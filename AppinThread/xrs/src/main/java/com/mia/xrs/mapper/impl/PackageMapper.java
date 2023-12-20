package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.*;
import com.mia.xrs.entity.Package;
import com.mia.xrs.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PackageMapper implements Mapper<Package, PackageDto> {

    private final LetterMapper letterMapper;

    @Override
    public PackageDto toDto(Package aPackage) {

        return PackageDto.builder()
                .id(aPackage.getId())
                .form(FormDto.builder()
                        .id(aPackage.getForm().getId())
                        .formNumber(aPackage.getForm().getFormNumber())
                        .build())
                .packageNo(aPackage.getPackageNo())
                .letters(aPackage.getLetters().stream()
                        .map(letterMapper::toDto)
                        .collect(Collectors.toList()))
                .sentDate(aPackage.getSentDate())
                .receiveDate(aPackage.getReceiveDate())
                .senderSignature(aPackage.getSenderSignature())
                .receiverSignature(aPackage.getReceiverSignature())
                .createdBy(UserDto.builder()
                        .id(aPackage.getCreatedBy().getId())
                        .firstName(aPackage.getCreatedBy().getUserSpecification().getFirstName())
                        .lastName(aPackage.getCreatedBy().getUserSpecification().getLastName())
                        .fatherName(aPackage.getCreatedBy().getUserSpecification().getFatherName())
                        .policeCard(aPackage.getCreatedBy().getUserSpecification().getPoliceCard())
                        .rank(aPackage.getCreatedBy().getUserSpecification().getRank())
                        .department(DepartmentDto.builder()
                                .id(aPackage.getCreatedBy().getUserSpecification().getDepartment().getId())
                                .name(aPackage.getCreatedBy().getUserSpecification().getDepartment().getName())
                                .parentId(aPackage.getCreatedBy().getUserSpecification().getDepartment().getParentId())
                                .build())
                        .build())
                .letterCount(aPackage.getLetterCount())
                .build();
    }

    @Override
    public Package toEntity(PackageDto packageDto) {
        return null;
    }
}