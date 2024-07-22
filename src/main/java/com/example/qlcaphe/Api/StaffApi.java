package com.example.qlcaphe.Api;

import com.example.qlcaphe.Entity.StaffEntity;
import com.example.qlcaphe.Service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/staff-api")
public class StaffApi {
    @Autowired
    private StaffService staffServ;

    @GetMapping("/getAllAccount")
    public ResponseEntity<?> doGetAllAccount() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", staffServ.getAllStaff());
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/getAllAccount ", e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> doPostLogin(@RequestBody StaffEntity staff) {
        Map<String, Object> result = new HashMap<>();
        try {
            var data = staffServ.getAccountByTkAndMK(staff.getAccount(), staff.getPassword());
            if (!data.isEmpty()) {
                result.put("status", true);
                result.put("message", "Login Success");
                result.put("data", data);

            } else {
                result.put("status", false);
                result.put("message", "Login Fail");
            }
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Login Fail");
            result.put("data", null);
            log.error("Fail When Call API /accountApi/login ", e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<?> doPostAddAccount(@RequestBody StaffEntity staff) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", staffServ.doSaveAccount(staff));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/getAllAccount ", e);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/DeleteId")
    public ResponseEntity<?> doDeleteUserId(@RequestParam("Staff_Id") int Staff_Id) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Call Api Success");
            staffServ.doDeleteById(Staff_Id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call Api Error");
            result.put("data", null);
            System.out.println(e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/admin/item")
    public ResponseEntity<?> doGetItem(StaffEntity staff) {
        Map<String, Object> kq = new HashMap<>();
        try {
            var data = staffServ.getAccountByTkAndMK(staff.getAccount(), staff.getPassword());
            if (!data.isEmpty()) {
                kq.put("status", true);
                kq.put("message", "Login Success");
                kq.put("data", data);
            } else {
                kq.put("status", false);
                kq.put("message", "Login Fail");
            }
        } catch (Exception e) {
            kq.put("status", false);
            kq.put("message", "Login Fail");
            kq.put("data", null);
            log.error("Fail When Call API /accountApi/login ", e);
        }
        return ResponseEntity.ok(kq);
    }

    @GetMapping("/getStaffByStaffId")
    public ResponseEntity<?> doGetStaffByStaffId(@RequestParam("Staff_id") int Staff_id) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", staffServ.getStaffByStaffId(Staff_id));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}

