package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Repository.CustomerRepository;
import com.example.qlcaphe.Service.registerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class registerServiceImpl implements registerService {
    final CustomerRepository repo;
//    final mailService mailService;
    @Override
    public CustomersEntity doSaveCustomer(CustomersEntity customersEntity) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customersEntity.setPassword(passwordEncoder.encode(customersEntity.getPassword()));

//        mailE mail = new mailE();
//        mail.setTo(accountEntity.getEmail());
//        mailService.sendMail(mail);
        return repo.save(customersEntity);
    }
}
