package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.DepartmentDto;
import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.UserDto;
import com.mia.xrs.entity.Letter;
import com.mia.xrs.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LetterMapper implements Mapper<Letter, LetterDto> {

    @Override
    public LetterDto toDto(Letter letter) {

        return LetterDto.builder()
                .id(letter.getId())
                .uniqueId(letter.getUniqueId())
                .letterNo(letter.getLetterNo())
                .fromDepartment(DepartmentDto.builder()
                        .id(letter.getFromDepartment().getId())
                        .name(letter.getFromDepartment().getName())
                        .parentId(letter.getFromDepartment().getParentId())
                        .build())
                .toDepartment(DepartmentDto.builder()
                        .id(letter.getToDepartment().getId())
                        .name(letter.getToDepartment().getName())
                        .parentId(letter.getToDepartment().getParentId())
                        .build())
                .importanceDegree(letter.getImportanceDegree())
                .envelope(letter.getEnvelope())
                .parcel(letter.getParcel())
                .createdBy(UserDto.builder()
                        .id(letter.getCreatedBy().getId())
                        .username(letter.getCreatedBy().getUsername())
                        .password(letter.getCreatedBy().getPassword())
                        .authorities(letter.getCreatedBy().getAuthorities().toString())
                        .firstName(letter.getCreatedBy().getUserSpecification().getFirstName())
                        .lastName(letter.getCreatedBy().getUserSpecification().getLastName())
                        .fatherName(letter.getCreatedBy().getUserSpecification().getFatherName())
                        .policeCard(letter.getCreatedBy().getUserSpecification().getPoliceCard())
                        .rank(letter.getCreatedBy().getUserSpecification().getRank())
                        .department(DepartmentDto.builder()
                                .id(letter.getCreatedBy().getUserSpecification().getDepartment().getId())
                                .name(letter.getCreatedBy().getUserSpecification().getDepartment().getName())
                                .parentId(letter.getCreatedBy().getUserSpecification().getDepartment().getParentId())
                                .build())
                        .build())
                .date(letter.getDate())
                .note(letter.getNote())
                .build();
    }

    @Override
    public Letter toEntity(LetterDto letterDto) {
        return null;
    }
}