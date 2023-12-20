package com.mia.xrs.dto;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LetterDto {

    private Integer id;

    private Integer uniqueId;

    private Integer letterNo;

    private DepartmentDto fromDepartment;

    private DepartmentDto toDepartment;

    private String importanceDegree;

    private Integer envelope;

    private Integer parcel;

    private UserDto createdBy;

    private Date date;

    private String note;
}
