package com.giassi.expenses.rest.services;

import com.giassi.expenses.rest.entities.Gender;
import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.exceptions.InvalidDataException;
import com.giassi.expenses.rest.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    // getUserById

    // null id
    @Test(expected = InvalidDataException.class)
    public void given_null_user_when_getUserById_throw_exception() {
        userService.getUserById(null);
    }

    // not existing
    @Test(expected = InvalidDataException.class)
    public void given_not_existing_user_when_getUserById_throw_exception() {
        userService.getUserById(99L);
    }

    // existing user
    @Test
    public void given_existing_user_when_getUserById_returning_existing_validUser() {
        User demoUserData = getDemoUserData();

        when(userRepository.findById(1L)).thenReturn(Optional.of(demoUserData));

        User user = userService.getUserById(1L);

        assertNotNull(user);

        assertEquals(1L, user.getId());
        assertEquals("andrea", user.getUsername());
        assertEquals("Test!123", user.getPassword());
        assertEquals("Andrea", user.getName());
        assertEquals("Giassi", user.getSurname());
        assertEquals("MALE", user.getGender().name());
        assertEquals("text note", user.getNote());
        assertEquals("2021-02-09T12:30:12", user.getCreationDt().toString());
        assertEquals("2021-02-09T13:05", user.getUpdatedDt().toString());
        assertEquals("2021-02-09T13:20", user.getLoginDt().toString());
    }

    public static User getDemoUserData() {
        User user = new User();
        user.setId(1L);
        user.setUsername("andrea");
        user.setPassword("Test!123");
        user.setName("Andrea");
        user.setSurname("Giassi");
        user.setGender(Gender.MALE);
        user.setNote("text note");
        user.setCreationDt(LocalDateTime.of(2021, 2, 9, 12, 30, 12));
        user.setUpdatedDt(LocalDateTime.of(2021, 2, 9, 13, 5));
        user.setLoginDt(LocalDateTime.of(2021, 2, 9, 13, 20));
        return user;
    }

}
