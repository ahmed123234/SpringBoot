package com.example.springactuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuators")
public class SimpleController {
    @GetMapping("/execute")
    public String execute(){
        return "invoked";
    }
}
