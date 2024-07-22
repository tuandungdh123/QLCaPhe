package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.Entity.StaffEntity;
import com.example.qlcaphe.Repository.StaffRepository;
import com.example.qlcaphe.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    final StaffRepository repo;
    @Override
    public List<StaffEntity> getAllStaff(){
        return repo.findAll();
    };

    @Override
    public Optional<String> getAccountByTkAndMK(String account, String password){
        return Optional.ofNullable(account);
    }
    @Override
    public void doDeleteById(int Staff_Id) {
        repo.deleteById(Staff_Id);
    }
    @Override
    public StaffEntity doSaveAccount(StaffEntity staff){
        return repo.save(staff);
    }

    @Override
    public Optional<Optional<StaffEntity>> getStaffByStaffId(int Staff_id){
        var result = repo.findById(Staff_id);
        return Optional.ofNullable(result);
    }
}
