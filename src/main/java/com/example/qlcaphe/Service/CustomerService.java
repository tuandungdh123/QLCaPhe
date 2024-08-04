package com.example.qlcaphe.Service;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.exception.AppException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomersEntity> getAllCustomers() throws AppException;
    Optional<String> getCustomersByTkAndMk(String username, String password) throws SQLException, AppException;
}
