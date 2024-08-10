package com.example.qlcaphe.Service;

import com.example.qlcaphe.Entity.CustomerDetailE;

public interface CustomerDetailService {

    CustomerDetailE SaveCustomerDetails(CustomerDetailE customerDetailE);

    // Lấy thông tin chi tiết khách hàng
    CustomerDetailE getCustomerDetails(int customerId);
}
