package com.giassi.expenses.rest.dtos.inputs;

import lombok.Data;

@Data
public class UserRegistrationInput {

    private String username;
    private String password;

    private String name;
    private String surname;
    private String gender;

}
