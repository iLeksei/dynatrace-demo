package com.example.dynatracedemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ClientController {

    @GetMapping(value = "/site")
    public String getMainPage() {
        return "index";
    }

}
