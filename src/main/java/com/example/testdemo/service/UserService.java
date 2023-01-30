package com.example.testdemo.service;

import com.example.testdemo.domain.po.User;

public interface UserService {

    void insert(User user);

    void delete(Long id);

    void update(User user);

    User get();
}
