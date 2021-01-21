package com.example.articlestore.service;

import com.example.articlestore.domain.User;

public interface UserService {
    User save(User user);

    User update(User user);

    User findByUsername(String username);

    User updateProfile(User oldUser, User form);

    void subscribe(User subscriber, User user);

    void unsubscribe(User subscriber, User user);
}
