package com.mia.xrs.service;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.PackageDto;
import org.springframework.data.domain.Page;

import java.sql.Date;

public interface PackageService {

    Page<PackageDto> findAllPage(Integer pageSize,
                                 Integer pageNumber,
                                 String[] sortBy);

    PackageDto findById(Integer id);

    PackageDto findByPackageNo(Integer packageNo);

    Page<PackageDto> findByDate(Date sentDate,
                               Integer pageSize,
                               Integer pageNumber,
                               String[] sortBy);

    Page<PackageDto> findByFromDepartment(String name,
                                         Integer pageSize,
                                         Integer pageNumber,
                                         String[] sortBy);

    Page<PackageDto> findByToDepartment(String name,
                                       Integer pageSize,
                                       Integer pageNumber,
                                       String[] sortBy);

    Page<PackageDto> findByCreatedBy(String createdBy,
                                    Integer pageSize,
                                    Integer pageNumber,
                                    String[] sortBy);

    PackageDto save(PackageDto packageDto);

    PackageDto update(Integer id,PackageDto packageDto);

    void delete(Integer id);
}
