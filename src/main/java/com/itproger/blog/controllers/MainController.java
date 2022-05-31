package com.itproger.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {



    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "Главная Страница");
        return "home";
    }
    @GetMapping("/about")
    public String about( Model model) {
        model.addAttribute("title", "Главная Страница");

        return "about";
    }

    @PostMapping("/generate")
    public String generate(@RequestParam("CountOfPics") String CountOfPics) {



        return "Ok";
    }
}