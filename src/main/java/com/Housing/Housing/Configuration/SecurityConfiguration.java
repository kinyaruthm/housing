package com.Housing.Housing.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Autowired private final AuthenticationProvider authenticationProvider;
    //add apis to be whitelisted -- that doesn't require authentication
    private static final String [] whitelist = {
            "/api/adduser",
            "/api/get-users",
            "api/getAuser",
            "api/updateUser",
            "api/deleteUser",
            "api/confirm-code",
            "api/dynamic-Update",
            "api/resend-code",
            "api/login"

    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequest) ->
                        authorizeHttpRequest.requestMatchers(whitelist)
                                .permitAll().anyRequest().authenticated())
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();

    }


}
