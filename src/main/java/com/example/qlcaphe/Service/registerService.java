package com.example.qlcaphe.Service;

import com.example.qlcaphe.Entity.CustomersEntity;


public interface registerService {
    CustomersEntity doSaveCustomer(CustomersEntity accountEntity) throws Exception;
}
