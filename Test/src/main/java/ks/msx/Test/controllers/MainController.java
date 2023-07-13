package ks.msx.Test.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping
    public String guestApi(){
        return "guest";
    }

    @GetMapping("/user")
    @PreAuthorize("READ")
    public String userApi(){
        return "user";
    }

    @GetMapping("/admin")
    @PreAuthorize("WRITE")
    public String adminApi(){
        return "admin";
    }
}
