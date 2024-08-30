package com.Housing.Housing.Configuration;

import com.Housing.Housing.Service.AppUserService;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailValidators {

    @Bean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }
}
