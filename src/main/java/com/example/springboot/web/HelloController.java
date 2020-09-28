package com.example.springboot.web;

import com.example.springboot.domain.exception.UserNotExistException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello() {
        return "SpringBoot Hello World";
    }

    @GetMapping("/{id:\\d+}")
    public void get(@PathVariable String id) {
        throw new UserNotExistException(id);
    }
}
