package com.Housing.Housing.Service;

import com.Housing.Housing.Model.AppUser;
import com.Housing.Housing.Model.Verification;
import com.Housing.Housing.Repository.AppUserRepository;
import com.Housing.Housing.Repository.VerificationRepository;
import com.Housing.Housing.Utils.BasicResponse;
import com.Housing.Housing.Utils.Send;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationService {
    @Autowired
    private VerificationRepository verificationRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private final Send emailsend;


    public BasicResponse createToken(AppUser appUser) {
        String code = generateTokens();
        Verification verification = new Verification(
                code,
                LocalDateTime.now().plusMinutes(5),
                "verification",
                appUser
        );
        verificationRepository.save(verification);
        String message = "verification code is " + code;
        emailsend.send(appUser.getEmailAddress(), message);
        return BasicResponse.OfSuccess("Verification code has been sent to your email");


    }

    private String generateTokens() {
        Random rand = new Random();
        String code = String.format("%04d", rand.nextInt(10000));
        return code;

    }

    public BasicResponse confirmCode(String code) {
        Optional<Verification> user = Optional.ofNullable(verificationRepository.findByCode(code));
        if (user.isPresent()) {
            if (user.get().getExpirydate().isBefore(LocalDateTime.now())) {
                verificationRepository.delete(user.get());
                return BasicResponse.OfSuccess("Token has expired");
            }
            int update = appUserRepository.updateEnabled(true, user.get().getAppUser().getEmailAddress());
            if (update > 0) {
                verificationRepository.delete(user.get());
                return BasicResponse.OfSuccess("User verified");
            }
            return BasicResponse.Failure("Verification code not found");
        }
        return BasicResponse.Failure("token not found");
    }
}


