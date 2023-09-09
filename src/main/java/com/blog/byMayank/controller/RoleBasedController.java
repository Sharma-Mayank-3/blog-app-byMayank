package com.blog.byMayank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog-app/")
public class RoleBasedController {

    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/normal")
    public String getNormal(){
        return "normal";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

}
