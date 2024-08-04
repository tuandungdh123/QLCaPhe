package com.example.qlcaphe.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Staff")
public class StaffEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="Staff_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Staff_Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Account")
    private String account;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "Birthday")
    private String birthday;

    @Column(name = "Status")
    private String status;

    @Column(name = "is_Staff")
    private boolean isStaff;


}
