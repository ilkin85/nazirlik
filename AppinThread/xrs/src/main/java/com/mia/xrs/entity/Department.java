package com.mia.xrs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer parentId;

    @OneToMany(mappedBy = "fromDepartment")
    private List<Letter> letterFrom;

    @OneToMany(mappedBy = "toDepartment")
    private List<Letter> letterTo;

    @OneToMany(mappedBy = "department")
    private List<UserSpecification> userSpecifications;
}
