package com.example.springboot.mapper;

import com.example.springboot.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findById(int id);
}
