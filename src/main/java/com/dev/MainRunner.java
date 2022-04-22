package com.dev;

import com.dev.model.User;
import com.dev.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MainRunner implements CommandLineRunner {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private static final String REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.info("reading users");
        List<User> users = userService.readUsers(URL);
        log.info(String.valueOf(users));
        log.info("validating users");
        List<User> validatedUsers = userService.validateUsersByEmail(users, REGEX);
        log.info(String.valueOf(validatedUsers));
        log.info("saving users to db");
        userService.saveUsers(validatedUsers);
    }
}
