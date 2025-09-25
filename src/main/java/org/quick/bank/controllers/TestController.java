package org.quick.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {


    @GetMapping("/ind")
    public String nn() {
        return "gg";
    }
}
