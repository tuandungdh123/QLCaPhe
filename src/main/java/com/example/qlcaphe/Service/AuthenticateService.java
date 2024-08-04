package com.example.qlcaphe.Service;

import java.sql.SQLException;

public interface AuthenticateService {
    Boolean isAdminTableContainsIsStaff() throws SQLException;
    Object authenticateUser(String username, String password) throws SQLException;
}
