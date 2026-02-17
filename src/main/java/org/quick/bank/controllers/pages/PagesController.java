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

    @GetMapping("/view_cards/{user_id}")
    public String viewCards(@PathVariable("user_id") Long id, Model model) {
        model.addAttribute("user_id", id);
        return "cards";
    }

    @GetMapping("/view_transactions/{user_id}")
    public String viewTransactions(@PathVariable("user_id") Long id, Model model) {
        model.addAttribute("user_id", id);
        return "transactions";
    }

    @GetMapping("/view_card_transactions/{card_id}")
    public String viewCardTransactions(@PathVariable("card_id") Long id, Model model) {
        model.addAttribute("card_id", id);
        return "card-transactions";
    }

    @GetMapping("/view_transaction/{transaction_id}")
    public String viewTransaction(@PathVariable("transaction_id") Long id, Model model) {
        model.addAttribute("transaction_id", id);
        return "transaction";
    }
}