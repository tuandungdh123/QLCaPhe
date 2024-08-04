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
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
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
    public Object authenticateUser(String username, String password) {
        // Kiểm tra xem bảng admin có cột isstaff hay không
        if (isAdminTableContainsIsStaff()) {
            // Nếu có, kiểm tra thông tin đăng nhập trong bảng Admin
            StaffEntity admin = staffRepository.findStaffByAccountAndPassword(username, password);
            if (admin != null) {
                return admin;
            }
        }

        // Nếu không có hoặc không tìm thấy người dùng trong bảng Admin, kiểm tra trong bảng KhachHang
        CustomersEntity khachHang = customerRepository.findCustomersByUsernameAndPassword(username, password);
        if (khachHang != null) {
            return khachHang;
        }

        // Nếu không tìm thấy người dùng trong cả hai bảng, trả về null
        return null;
    }
}
