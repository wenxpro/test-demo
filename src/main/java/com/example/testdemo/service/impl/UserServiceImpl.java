package com.example.testdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testdemo.dao.UserMapper;
import com.example.testdemo.domain.po.User;
import com.example.testdemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void insert(User user) {
        super.save(user);
    }

    @Override
    public void delete(Long id) {
        super.removeById(id);
    }

    @Override
    public void update(User user) {
        super.updateById(user);
    }

    @Override
    public User get() {
        return new User();
    }
}
