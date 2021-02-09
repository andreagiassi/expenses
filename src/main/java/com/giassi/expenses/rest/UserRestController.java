package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.UserDTO;
import com.giassi.expenses.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId) {
        UserDTO userDTO = new UserDTO(
                userService.getUserById(userId)
        );
        return ResponseEntity.ok(userDTO);
    }

}
