package com.emrekp.haciko.controller;

import com.emrekp.haciko.entity.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("entry", new Entry());

        return "index";
    }

    @PostMapping("/")
    public String post(@ModelAttribute Entry entry, Model model) {
        return "redirect:/index";
    }
}
