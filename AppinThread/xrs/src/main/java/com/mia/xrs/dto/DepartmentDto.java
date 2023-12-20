package com.mia.xrs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {

    private Integer id;

    private String name;

    @JsonIgnore
    private Integer parentId;
}
