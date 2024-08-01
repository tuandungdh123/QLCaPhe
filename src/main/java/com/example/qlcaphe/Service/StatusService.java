package com.example.qlcaphe.Service;

import com.example.qlcaphe.Entity.StatusEntity;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    List<StatusEntity> getAllStatus();
    Optional<StatusEntity> getStatusByStatusName (String Status_Name);
}
