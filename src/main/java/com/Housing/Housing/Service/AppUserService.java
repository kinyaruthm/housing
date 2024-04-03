package com.Housing.Housing.Service;

import com.Housing.Housing.Databind.Request.RegistrationRequest;
import com.Housing.Housing.Model.AppUser;
import com.Housing.Housing.Repository.AppUserRepository;
import com.Housing.Housing.Utils.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    @Autowired
    private final AppUserRepository appUserRepository;
    @Autowired
    private final PasswordEncoder passwordencoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <AppUser> appUser= Optional.ofNullable(appUserRepository.findByUserName(username));
        if(appUser.isEmpty()){
            throw new UsernameNotFoundException("UserName not found");
        }
        return new User(appUser.get().getUsername(), appUser.get().getPassword(),appUser.get().getAuthorities());
    }

    public BasicResponse UserRegistration (RegistrationRequest request){
        AppUser appUser=new AppUser(
                request.getFirstName(),
                request.getSecondName(),
                request.getEmailAddress(),
                request.getPhoneNumber(),
                request.getUserName(),
                passwordencoder.encode(request.getPassword()),
                request.getRole()
        );
        appUserRepository.save(appUser);
        return BasicResponse.OfSuccess();
    }

}
