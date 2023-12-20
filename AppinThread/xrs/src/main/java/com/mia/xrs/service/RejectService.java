package com.mia.xrs.service;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.RejectDto;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.util.Optional;

public interface RejectService {

    Page<RejectDto> findAllPage(Integer pageSize,
                                Integer pageNumber,
                                String[] sortBy);

    RejectDto findById(Integer id);

    RejectDto findByRouteNo(String routeNo);

    Page<RejectDto> findByReturnDate(Date date,
                               Integer pageSize,
                               Integer pageNumber,
                               String[] sortBy);

    RejectDto findByLetterNo(Integer letterNo, Boolean status);

    RejectDto save(RejectDto rejectDto);

    RejectDto update(Integer id,RejectDto rejectDto);

    void delete(Integer id);
}
