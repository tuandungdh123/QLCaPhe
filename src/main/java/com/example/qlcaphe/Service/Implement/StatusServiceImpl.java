package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.StatusEntity;
import com.example.qlcaphe.Repository.StatusRepo;
import com.example.qlcaphe.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepo statusRepo;

    @Override
    public List<StatusEntity> getAllStatus() {
        return statusRepo.findAll();
    }

    @Override
    public Optional<StatusEntity> getStatusByStatusName(String Status_Name) {
        var result = statusRepo.findByStatus_Name(Status_Name);
        return result;
    };
}
