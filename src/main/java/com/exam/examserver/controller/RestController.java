package com.exam.examserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/kishan")
    @ResponseBody
    public String home() {
        return "hello, welcome to my website";
    }
}
