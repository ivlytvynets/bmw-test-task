package com.dev.service;

import com.dev.model.User;

import java.util.List;

public interface UserService {

    List<User> readUsers(String url);
    void saveUsers(List<User> users);

    List<User> validateUsersByEmail(List<User> users, String regex);
}
