package ks.msx.Test.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping
    public String guestApi(){
        return "Api for Guest";
    }

    @GetMapping("/user")
    public String userApi(){
        return "Api for User";
    }

    @GetMapping("/admin")
    public String adminApi(){
        return "Api for Admin";
    }
}
