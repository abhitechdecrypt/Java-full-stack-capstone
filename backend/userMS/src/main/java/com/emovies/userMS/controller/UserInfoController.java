package com.emovies.userMS.controller;

import com.emovies.userMS.DAO.ResponseDTO;
import com.emovies.userMS.DAO.UserLoginDTO;
import com.emovies.userMS.Entity.UserInfo;
import com.emovies.userMS.service.UserInfoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/movies")
@RestController
public class UserInfoController {
    @Value("${userService.application.message}")
    private String applicationMessage;

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @GetMapping
    public ResponseEntity<ResponseDTO<String>> getMessage() {
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setMessage("Success");
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(applicationMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("register-user")
    public ResponseEntity<ResponseDTO<UserInfo>> registerUser(@Valid @RequestBody UserInfo userInfo) {
        ResponseDTO<UserInfo> response = new ResponseDTO<>();

        try {
            UserInfo registeredUser = userInfoService.registerUser(userInfo);

            if (registeredUser != null) {
                response.setMessage("User Registered Successfully!");
                response.setStatusCode(HttpStatus.CREATED.value());
                response.setData(registeredUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.setMessage("Registration failed. Please try again.");
                response.setStatusCode(HttpStatus.BAD_REQUEST.value()); // Adjust status code as appropriate
                response.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("login-user")
    public ResponseEntity<ResponseDTO<UserInfo>> userLogin( @RequestBody UserLoginDTO userLoginDTO) {
        ResponseDTO<UserInfo> response = new ResponseDTO<>();

        try {
            UserInfo loginUser = userInfoService.loginUser(userLoginDTO);

            if (loginUser != null) {
                response.setMessage("User Login Successfully!");
                response.setStatusCode(HttpStatus.OK.value());
                response.setData(loginUser);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.setMessage("Login failed. Please try again.");
                response.setStatusCode(HttpStatus.BAD_REQUEST.value()); // Adjust status code as appropriate
                response.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
