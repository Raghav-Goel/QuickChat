package com.chatApplication.QuickChat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Hello");
        model.addAttribute("name","Hello and welcome");
        return "home";
    }
    
    
}
