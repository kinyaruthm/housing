package com.Housing.Housing.Controller;

import com.Housing.Housing.Databind.Request.LoginRequest;
import com.Housing.Housing.Databind.Request.RegistrationRequest;
import com.Housing.Housing.Model.AppUser;
import com.Housing.Housing.Service.AppUserService;
import com.Housing.Housing.Service.VerificationService;
import com.Housing.Housing.Utils.BasicResponse;
import jakarta.persistence.Basic;
import lombok.RequiredArgsConstructor;
import org.apache.naming.factory.SendMailFactory;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
//Handles all http requests - creating apis
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService appUserService;
    private final VerificationService verificationService;

    @PostMapping(path = "/adduser")
    public BasicResponse addUser(@RequestBody RegistrationRequest Request) {
        return appUserService.UserRegistration(Request);
    }

    @GetMapping(path = "/get-users")
    public BasicResponse getUsers() {
        return appUserService.AppUserList();
    }

    @GetMapping(path = "/getAuser")
    public BasicResponse getAUser(@RequestParam("email") String email) {

        return appUserService.getAUser(email);
    }

    @PutMapping(path = "/updateUser")
    public BasicResponse update(@RequestBody RegistrationRequest Request, @RequestParam("email") String email) {
        return appUserService.updateUser(Request, email);
    }

    @PostMapping(path = "/deleteUser")
    public BasicResponse deleteUser(@RequestParam("email") String email) {
        return appUserService.deleteUser(email);
    }

    @PostMapping(path = "/dynamic-Update")
    public BasicResponse dynamicUpdate(@RequestParam("email") String email, @RequestBody Map<String, String> Request) {
        return appUserService.DynamicUpdate(email,Request);

    }
    @PostMapping(path="/confirm-code")
    public BasicResponse confirmCode(@RequestParam("code") String code)
    {
        return verificationService.confirmCode(code);
    }

    @GetMapping(path="/resend-code")
    public BasicResponse resendCode(@RequestParam("email")String email){
        return appUserService.resendCode(email);
    }

    @PostMapping(path = "/login")
    public BasicResponse login(@RequestBody LoginRequest loginRequest) {
        return appUserService.loginUser(loginRequest);
    }

}
