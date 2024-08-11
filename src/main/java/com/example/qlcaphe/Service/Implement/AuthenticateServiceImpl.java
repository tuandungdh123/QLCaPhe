package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.CustomersEntity;
import com.example.qlcaphe.Entity.StaffEntity;
import com.example.qlcaphe.Repository.CustomerRepository;
import com.example.qlcaphe.Repository.StaffRepository;
import com.example.qlcaphe.Service.AuthenticateService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Phương thức kiểm tra xem bảng admin có cột isstaff hay không
    public Boolean isAdminTableContainsIsStaff() {
        EntityType<StaffEntity> entityType = entityManager.getMetamodel().entity(StaffEntity.class);
        return entityType.getDeclaredAttributes().stream()
                .anyMatch(attribute -> attribute.getName().equals("isStaff"));
    }

    // Phương thức xác thực người dùng dựa trên username và password
    public Object authenticateUser(String username) {
        // Kiểm tra xem bảng admin có cột isstaff hay không
        if (isAdminTableContainsIsStaff()) {
            // Nếu có, kiểm tra thông tin đăng nhập trong bảng Admin
            StaffEntity admin = staffRepository.findStaffByAccount(username);
            if (admin != null && passwordEncoder.matches(admin.getPassword(), passwordEncoder.encode(admin.getPassword()))) {
                return admin;
            }
        }
        // Nếu không có hoặc không tìm thấy người dùng trong bảng Admin, kiểm tra trong bảng KhachHang
        CustomersEntity khachHang = customerRepository.findCustomersByUsername(username);
        if (khachHang != null && passwordEncoder.matches(khachHang.getPassword(), passwordEncoder.encode(khachHang.getPassword()))) {
            return khachHang;
        }

        // Nếu không tìm thấy người dùng trong cả hai bảng, trả về null
        return null;
    }
}
