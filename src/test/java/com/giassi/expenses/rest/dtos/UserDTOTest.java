package com.giassi.expenses.rest.dtos;

import com.giassi.expenses.rest.entities.Gender;
import com.giassi.expenses.rest.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test UserDTO
 */
public class UserDTOTest {

    @Test
    public void createUserDTO() {
        User user = new User();
        user.setId(1L);
        user.setUsername("andrea");
        user.setPassword("Test!123");
        user.setName("Andrea");
        user.setSurname("Giassi");
        user.setGender(Gender.MALE);
        user.setNote("text note");
        user.setCreationDt(LocalDateTime.of(2021,2,9,12,30,12));
        user.setUpdatedDt(LocalDateTime.of(2021, 2, 9, 13, 5));
        user.setLoginDt(LocalDateTime.of(2021,2,9,13,20));

        UserDTO userDTO = new UserDTO(user);

        // check on user dto
        Assert.notNull(userDTO, "user dto null!");
        assertEquals(1L, userDTO.getId());
        assertEquals("andrea", userDTO.getUsername());
        assertEquals("Andrea", userDTO.getName());
        assertEquals("Giassi", userDTO.getSurname());
        assertEquals("MALE", userDTO.getGender());
    }

}
