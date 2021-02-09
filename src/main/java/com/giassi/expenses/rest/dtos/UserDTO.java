package com.giassi.expenses.rest.dtos;

import com.giassi.expenses.rest.entities.User;
import lombok.Data;

@Data
public class UserDTO {

    public UserDTO() {
        //
    }

    public UserDTO(User user) {
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
