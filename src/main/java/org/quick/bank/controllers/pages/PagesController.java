package org.quick.bank.controllers.pages;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class PagesController {

    @GetMapping
    public String homePage() {
        return "index";
    }

    @GetMapping("/view_cards/{email}")
    public String viewCards(@PathVariable("email") String email) {
        
        return "cards";
    }

}
