package com.itproger.blog.controllers;

import com.itproger.blog.domain.User;
import com.itproger.blog.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final ImageRepo imageRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }
    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null) {
            data.put("profile", user);
            data.put("images", imageRepo.findAll(Sort.by(Sort.Order.desc("id"))));
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "home";
    }

}