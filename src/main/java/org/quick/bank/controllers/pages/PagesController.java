package org.quick.bank.controllers.pages;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/view_cards/{id}")
    public String viewCards(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user_id", id);
        return "cards";
    }

    @GetMapping("/view_transactions/{id}")
    public String viewTransactions(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user_id", id);
        return "transactions";
    }
}