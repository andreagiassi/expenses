package com.giassi.expenses.rest.services;

import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.exceptions.InvalidDataException;
import com.giassi.expenses.rest.exceptions.UserNotFoundException;
import com.giassi.expenses.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

}
