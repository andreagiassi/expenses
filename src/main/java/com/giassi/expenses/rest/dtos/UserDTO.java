package com.giassi.expenses.rest.dtos;

import com.giassi.expenses.rest.entities.User;
import lombok.Data;

// the user profile
@Data
public class UserDTO {

    public UserDTO() {
        // empty impl
    }

    public UserDTO(User user) {
        if (user == null)   return;

        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.gender = user.getGender().name();
    }

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String gender;

}
