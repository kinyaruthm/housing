package com.Housing.Housing.Controller;

import com.Housing.Housing.Databind.Request.RegistrationRequest;
import com.Housing.Housing.Service.AppUserService;
import com.Housing.Housing.Utils.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService appUserService;
    @PostMapping(path = "/adduser")
    public BasicResponse addUser (@RequestBody RegistrationRequest Request){
        return appUserService.UserRegistration(Request);


    }

}
