package com.example.qlcaphe.Service;

import com.example.qlcaphe.Entity.StaffEntity;
import com.example.qlcaphe.exception.AppException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<StaffEntity> getAllStaff();
    Optional<String> getAccountByTkAndMK(String account, String password) throws SQLException, AppException;
    StaffEntity doSaveAccount(StaffEntity accountEntity);
    void  doDeleteById(int Staff_id);
    Optional<Optional<StaffEntity>> getStaffByStaffId(int Staff_id);
    Optional<String> findStaffByAccount(String username) throws AppException;
}
