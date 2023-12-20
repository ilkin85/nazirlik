package com.mia.xrs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    private String username;

    private String password;

    private String authorities;

    private String firstName;

    private String lastName;

    private String fatherName;

    private String policeCard;

    private String rank;

    private DepartmentDto department;
}
