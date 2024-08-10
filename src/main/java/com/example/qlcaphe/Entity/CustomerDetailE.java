package com.example.qlcaphe.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer_Details")
public class CustomerDetailE {

        @Id
        @Column(name = "Detail_Id")
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int detailId;

        @ManyToOne
        @JoinColumn(name = "Customer_Id", nullable = false)
        private CustomersEntity customer;

        @Column(name = "Full_Name")
        private String fullName;

        @Column(name = "Date_Of_Birth")
        private java.sql.Date dateOfBirth;

        @Column(name = "Email")
        private String email;

        @Column(name = "Default_Address")
        private String defaultAddress;

        @Column(name = "Gender")
        private String gender;

        @Column(name = "Profile_Image")
        private String profileImage;
}
