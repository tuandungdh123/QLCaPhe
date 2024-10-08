package com.example.qlcaphe.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping({"/login","/"})
    public String getLogin() {
        return "Login";
    }
    @GetMapping("/register")
    public String getLogout() {
        return "Register";
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
        return "Pages/Home/Order";
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

    @GetMapping("/Product_Managerment")
    public String getProduct_Managerment(){
        return "Pages/Home/Home_User/Product_Managerment";
    }

    @GetMapping("/Payment")
    public String getPayment(){
        return "Pages/Home/Home_User/Payment";
    }

    @GetMapping("/MyOrder")
    public String getMyOrder(){
        return "Pages/Home/Home_User/MyOrder";
    }


    @GetMapping("/nav")
    public String getNav(){
        return "Pages/Home/Home_User/Footer/Footer";
    }
}
