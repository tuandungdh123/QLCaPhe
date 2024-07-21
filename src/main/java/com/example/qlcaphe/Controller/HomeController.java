package com.example.qlcaphe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping({"/login","/"})
    public String getLogin() {
        return "Login";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "Pages/Home/admin";
    }

    @GetMapping("/productManagement")
    public String getProductManagement(){
        return "Pages/Home/OrderManagement";
    }

    @GetMapping("/staffManagement")
    public String getStaffManagement(){
        return "Pages/Home/Staff";
    }

    @GetMapping("/admin/oderManagement")
    public String getOderManagement(){
        return "Pages/Home/OrderManagement";
    }
}
