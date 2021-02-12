package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.UserDTO;
import com.giassi.expenses.rest.dtos.inputs.UserRegistrationInput;
import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@Slf4j
public class UserSignUpRestController {

    @Autowired
    private UserService userService;

    //  Simply user registration
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserRegistrationInput input) {
        User user = userService.signUp(input);
        return ResponseEntity.ok(new UserDTO(user));
    }

}
