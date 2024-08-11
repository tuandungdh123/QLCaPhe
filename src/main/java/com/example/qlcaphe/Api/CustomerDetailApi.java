package com.example.qlcaphe.Api;


import com.example.qlcaphe.Entity.CustomerDetailE;
import com.example.qlcaphe.Service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/CustomerDetail/Api")
public class CustomerDetailApi {
    @Autowired
    private CustomerDetailService customerDetailService;

    @PostMapping("/details")
    public CustomerDetailE postSaveCustomerDetailE(@RequestBody CustomerDetailE details) {
     return customerDetailService.SaveCustomerDetails(details);
    }

    @GetMapping("/getCustomerById")
    public CustomerDetailE getCustomerById(@RequestParam int customerId) {
        return customerDetailService.getCustomerDetails(customerId);
    }
}
