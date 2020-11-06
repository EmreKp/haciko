package com.emrekp.haciko.controller;

import com.emrekp.haciko.entity.Member;
import com.emrekp.haciko.entity.Post;
import com.emrekp.haciko.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    private final MainService service;

    @Autowired
    public MainController(MainService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", service.fetchAll());

        model.addAttribute("post", new Post());

        return "index";
    }

    @PostMapping("/")
    public String postMsg(@ModelAttribute Post post, RedirectAttributes redirectAttributes, Model model) {
        try {
            service.postMsg(post);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("member", new Member());

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute Member member, Model model) {
        service.registerMember(member);

        return "redirect:/";
    }
}
