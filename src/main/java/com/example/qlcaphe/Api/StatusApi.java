package com.example.qlcaphe.Api;

import com.example.qlcaphe.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/status-api")
public class StatusApi {
    @Autowired
    private StatusService roleService;
    @GetMapping("/getAllStatus")
    public ResponseEntity<?> doGetAllStatus(){
        Map<String, Object> result = new HashMap<>();
        result.put("success",true);
        result.put("message","Call Api Success");
        result.put("data",roleService.getAllStatus());
        return ResponseEntity.ok(result);

    }
    @GetMapping("/getFindByStatusName")
    public ResponseEntity<?> doGetFindByStatusName(@RequestParam("Status_Name") String Status_Name ){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", roleService.getStatusByStatusName(Status_Name));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
