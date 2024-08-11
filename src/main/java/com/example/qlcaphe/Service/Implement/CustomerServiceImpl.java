package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Repository.CustomerRepository;
import com.example.qlcaphe.Service.CustomerService;
import com.example.qlcaphe.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    final CustomerRepository customerRepository;
    @Override
    public List<CustomersEntity> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public Optional<String> getCustomersByTkAndMk(String username, String password) throws AppException {
        return Optional.ofNullable(username);
    }
    @Override
    public Optional<String> findCustomersByUsername(String username) throws AppException {
        return Optional.ofNullable(username);
    }
}
