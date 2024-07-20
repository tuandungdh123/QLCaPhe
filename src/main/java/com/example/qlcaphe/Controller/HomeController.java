package com.example.qlcaphe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping({"/login","/"})
    public String getLogin() {
        return "Login";
    }
}
