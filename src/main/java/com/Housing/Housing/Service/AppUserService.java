package com.Housing.Housing.Service;

import com.Housing.Housing.Databind.Enums.UserRole;
import com.Housing.Housing.Databind.Request.LoginRequest;
import com.Housing.Housing.Databind.Request.RegistrationRequest;
import com.Housing.Housing.Model.AppUser;
import com.Housing.Housing.Model.Verification;
import com.Housing.Housing.Repository.AppUserRepository;
import com.Housing.Housing.Repository.VerificationRepository;
import com.Housing.Housing.Utils.BasicResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordencoder;

    private final EmailValidator emailValidator;

    private final VerificationService verificationService;
    private final VerificationRepository verificationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = Optional.ofNullable(appUserRepository.findByUserName(username));
        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException("UserName not found");
        }
        return new User(appUser.get().getUsername(), appUser.get().getPassword(), appUser.get().getAuthorities());
    }

    public BasicResponse UserRegistration(RegistrationRequest request) {
        AppUser appUser = new AppUser(
                request.getFirstName(),
                request.getSecondName(),
                request.getEmailAddress(),
                request.getPhoneNumber(),
                request.getUserName(),
                passwordencoder.encode(request.getPassword()),
                request.getRole()
        );
        appUserRepository.save(appUser);
        return verificationService.createToken(appUser);

    }

    public BasicResponse AppUserList() {
        List<AppUser> appUserList = appUserRepository.findAll();
        return BasicResponse.OfSuccess(appUserList);
    }

    public BasicResponse getAUser(String email) {
        Optional<AppUser> appUser = Optional.ofNullable(appUserRepository.findByEmailAddress(email));
        if (appUser.isEmpty()) {
            return BasicResponse.Failure();
        }
        return BasicResponse.OfSuccess(appUser.get());
    }

    public BasicResponse updateUser(RegistrationRequest request, String email) {
        int appUser = appUserRepository.updateRole(request.getRole(), email);
        if (appUser > 0) {
            return BasicResponse.OfSuccess();
        }
        return BasicResponse.Failure();
    }

    public BasicResponse deleteUser(String email) {
        Optional<AppUser> appUser = Optional.ofNullable(appUserRepository.findByEmailAddress(email));
        if(appUser.isEmpty())
        {
            return BasicResponse.Failure("user doesn't exist");

        }
       //deleting related records
        verificationRepository.deleteByAppUser(appUser.get());

        appUserRepository.delete(appUser.get());
        return BasicResponse.OfSuccess("Deleted successfully");
    }

    public BasicResponse DynamicUpdate(String email, Map<String, String> request) {
        Optional<AppUser> user = Optional.ofNullable(appUserRepository.findByEmailAddress(email));
        if (user.isEmpty()) {
            return BasicResponse.Failure("User with the email does not exist");
        }

        AppUser appUser = user.get();
        if (request == null || request.isEmpty()) {
            return BasicResponse.Failure("Request is empty");
        }
        request.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    appUser.setFirstName(value);
                    break;
                case "secondName":
                    appUser.setSecondName(value);
                    break;
                case "email_address":
                    appUser.setEmailAddress(value);
                case "phoneNumber":
                    appUser.setPhoneNumber(value);
                    break;
               case "password":
                    appUser.setPassword(value);
                    break;
                default:
                System.out.println("Unexpected key: " + key);
            }
        });

        AppUser updatedUser = appUserRepository.save(appUser);
        return BasicResponse.OfSuccess(updatedUser);
    }

    public BasicResponse resendCode(String email) {
        Optional <AppUser> user= Optional.ofNullable(appUserRepository.findByEmailAddress(email));
        if (user.isEmpty())
        {
            return BasicResponse.Failure("user with the email doesnt exist");
        }
        return verificationService.createToken(user.get());

    }


    public BasicResponse loginUser(LoginRequest loginRequest) {
        // Retrieve the user by email from the LoginRequest object
        Optional<AppUser> user = Optional.ofNullable(appUserRepository.findByEmailAddress(loginRequest.getEmail()));
        if (user.isEmpty()) {
            return BasicResponse.Failure("User doesn't exist");
        }
        // Retrieve the AppUser object and check the password
        AppUser appUser = user.get();
        // Verify the password using passwordEncoder
        if (!passwordencoder.matches(loginRequest.getPassword(), appUser.getPassword())) {  // Use passwordEncoder to compare
            return BasicResponse.Failure("Incorrect password");
        }
        return BasicResponse.OfSuccess("Login successful");
    }

}

