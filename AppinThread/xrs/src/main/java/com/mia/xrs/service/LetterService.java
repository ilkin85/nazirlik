package com.mia.xrs.service;

import com.mia.xrs.dto.LetterDto;
import org.springframework.data.domain.Page;

import java.sql.Date;

public interface LetterService {

    Page<LetterDto> findAllPage(Integer pageSize,
                                Integer pageNumber,
                                String[] sortBy);

    LetterDto findById(Integer id);

    LetterDto findByLetterNo(Integer letterNo);

    Page<LetterDto> findByDate(Date date,
                               Integer pageSize,
                               Integer pageNumber,
                               String[] sortBy);

    Page<LetterDto> findByFromDepartment(String name,
                                         Integer pageSize,
                                         Integer pageNumber,
                                         String[] sortBy);

    Page<LetterDto> findByToDepartment(String name,
                                       Integer pageSize,
                                       Integer pageNumber,
                                       String[] sortBy);

    Page<LetterDto> findByCreatedBy(String createdBy,
                                    Integer pageSize,
                                    Integer pageNumber,
                                    String[] sortBy);

    LetterDto save(LetterDto letterDto);

    LetterDto update(Integer id,LetterDto letterDto);

    void delete(Integer id);
}
