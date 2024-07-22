package com.example.qlcaphe.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Status")
public class Status {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="Status_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Staff_Id;

    @Column(name = "Status_Name")
    private String Status_Name;
}
