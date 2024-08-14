package com.example.Mini.Project.Prodemi.Controller;

import com.example.Mini.Project.Prodemi.Validation.AuthenticationData;
import com.example.Mini.Project.Prodemi.Service.UserService;
import com.example.Mini.Project.Prodemi.Utility.AuthenticationRequest;
import com.example.Mini.Project.Prodemi.Utility.AuthenticationResponse;
import com.example.Mini.Project.Prodemi.Utility.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationData<AuthenticationResponse>> register (@Valid @RequestBody RegisterRequest request, Errors errors){
        AuthenticationData<AuthenticationResponse> responseData = new AuthenticationData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus("failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus("ok");
        responseData.setMessages(Collections.singletonList("success"));
        responseData.setPayload(userService.register(request));
        return ResponseEntity.ok(responseData);
    }
    @PostMapping("/login")
    public ResponseEntity <AuthenticationData<AuthenticationResponse>> login (@Valid @RequestBody AuthenticationRequest request, Errors errors){
        AuthenticationData<AuthenticationResponse> responseData = new AuthenticationData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus("failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus("ok");
        responseData.setMessages(Collections.singletonList("success"));
        responseData.setPayload(userService.authenticate(request));
        return ResponseEntity.ok(responseData);
    }
}
