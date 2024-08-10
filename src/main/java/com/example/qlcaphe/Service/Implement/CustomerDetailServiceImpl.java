package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.CustomerDetailE;
import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Repository.CustomerDetailRepo;
import com.example.qlcaphe.Repository.CustomerRepository;
import com.example.qlcaphe.Service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDetailRepo customerDetailRepo;

    // Phương thức thêm thông tin vào bảng Customer_Details

    @Override
    public CustomerDetailE SaveCustomerDetails(CustomerDetailE customerDetailE) {
        int customerId = customerDetailE.getCustomer().getCustomerId(); // Lấy customerId từ đối tượng details

        Optional<CustomersEntity> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            CustomersEntity customer = customerOptional.get();
            customerDetailE.setCustomer(customer); // Gán customer cho chi tiết
            return customerDetailRepo.saveAndFlush(customerDetailE);
        } else {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
    }

    // Lấy thông tin chi tiết khách hàng
    @Override
    public CustomerDetailE getCustomerDetails(int customerId) {
        return customerDetailRepo.findByCustomerCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer details not found for id: " + customerId));
    }

}
