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

    @GetMapping("/Home")
    public String getHome(){
        return "Pages/Home/Home_User/Home";
    }

    @GetMapping("/AllProduct")
    public String getAllProduct(){
        return "Pages/Home/Home_User/All_Product";
    }

    @GetMapping("/Account")
    public String getAccount(){
        return "Pages/Home/Home_User/Info_Account";
    }


    @GetMapping("/TuyenDung")
    public String getTuyenDung(){
        return "Pages/Home/Home_User/TrangTuyenDung";
    }
    @GetMapping("/Recruitment")
    public String getRecruitment(){
        return "Pages/Home/Home_User/Recruitment";
    }

    @GetMapping("/nav")
    public String getNav(){
        return "Pages/Home/Home_User/Footer/Footer";
    }
}
