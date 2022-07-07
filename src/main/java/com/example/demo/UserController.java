package com.example.demo;

import com.example.demo.data.User;
import com.example.demo.data.UserResponse;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
@Component
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService _userService;

    //TODO: updateUser by email, tests, code reformat, refactor , git
    @GetMapping("/users")
    public ResponseEntity<Object> getUsers(@RequestHeader Map<String, String> headers) {
        String token = headers.get("x-authentication-token");
        List<UserResponse> allUsers;
        if (!AuthService.isTokenValid(token)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            allUsers = _userService.getAllUsers();//make abother DTO to map respons
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(@RequestHeader Map<String, String> headers,
                                             @RequestBody Map<String, String> user) {
        String token = headers.get("x-authentication-token");
        if (!AuthService.isTokenValid(token)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        String email = user.get("email");
        String firstname = user.get("firstname");
        String lastname = user.get("lastname");
        try {
            if (!firstname.isEmpty()){
                _userService.updateUserFirstname(email, firstname);
            }
            if (!lastname.isEmpty()){
                _userService.updateUserLastname(email, lastname);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String jwt = "";
        String email = credentials.get("email");
        String password = credentials.get("password");
        try {
            if (_userService.checkLogIn(email, password)) {
                jwt = AuthService.generateJwe(email, password);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
            ;
        } catch (JoseException e) {
            System.out.println("error generating JWT");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.out.println("error login user");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String, String> newUser) {
        String jwt = "";
        String email = newUser.get("email");
        String password = newUser.get("password");
        try {
            _userService.createUser(newUser.get("firstname"),
                    newUser.get("lastname"),
                    email,
                    password
            );
            jwt = AuthService.generateJwe(email, password);
        } catch (Exception e) {
            //add logger
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(jwt);//return JWT
    }

}
