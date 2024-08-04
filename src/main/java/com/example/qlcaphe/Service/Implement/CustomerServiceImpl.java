package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Repository.CustomerRepository;
import com.example.qlcaphe.Service.CustomerService;
import com.example.qlcaphe.exception.AppException;
import com.example.qlcaphe.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public Optional<CustomersEntity> getCustomersByTk(String username) throws AppException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = customerRepository.getCustomersByTk(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authenticated = passwordEncoder.matches(user.getPassword(), user.getPassword());
        return Optional.ofNullable(user);
    }
}
