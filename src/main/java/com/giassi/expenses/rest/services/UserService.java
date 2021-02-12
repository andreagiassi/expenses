package com.giassi.expenses.rest.services;

import com.giassi.expenses.rest.dtos.inputs.UserRegistrationInput;
import com.giassi.expenses.rest.entities.Gender;
import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.exceptions.InvalidDataException;
import com.giassi.expenses.rest.exceptions.UserNotFoundException;
import com.giassi.expenses.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserById(final Long userId) {
        if (userId == null) {
            log.error("Invalid userId = null");
            throw new InvalidDataException("Invalid user Id");
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            log.error(String.format("User not found for userId = %d", userId));
            throw new UserNotFoundException("User doesn't exists for Id " + userId);
        }

        return userOpt.get();
    }

    // register a new user
    @Transactional
    public User signUp(final UserRegistrationInput input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
        user.setName(input.getName());
        user.setSurname(input.getSurname());
        user.setGender(Gender.getValidGender(input.getGender()));
        user.setCreationDt(LocalDateTime.now());

        user = userRepository.save(user);
        log.info("New user registration " + user.toString());

        return user;
    }

}
