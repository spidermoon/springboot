package com.example.springboot.service;

import com.example.springboot.domain.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(int id) {
        return userMapper.findById(id);
    }
}
