package com.mia.xrs.service.Impl;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.PackageDto;
import com.mia.xrs.entity.Letter;
import com.mia.xrs.entity.Package;
import com.mia.xrs.exception.NotFoundException;
import com.mia.xrs.mapper.impl.PackageMapper;
import com.mia.xrs.repository.DepartmentRepository;
import com.mia.xrs.repository.FormRepository;
import com.mia.xrs.repository.LetterRepository;
import com.mia.xrs.repository.PackageRepository;
import com.mia.xrs.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final LetterRepository letterRepository;
    private final FormRepository formRepository;
    private final PackageMapper packageMapper;

    @Override
    public Page<PackageDto> findAllPage(Integer pageSize, Integer pageNumber, String[] sortBy) {
        int defaultPageSize = 10;
        String[] defaultSortBy = {"packageNo"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return packageRepository.findByStatus(true, pageable)
                .map(packageMapper::toDto);
    }

    @Override
    public PackageDto findById(Integer id) {
        Package aPackage = packageRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new NotFoundException("Package by id : " + id + " not found"));

        return packageMapper.toDto(aPackage);    }

    @Override
    public PackageDto findByPackageNo(Integer packageNo) {
        Package aPackage = packageRepository.findByPackageNoAndStatus(packageNo, true);

        return packageMapper.toDto(aPackage);    }

    @Override
    public Page<PackageDto> findByDate(Date sentDate,
                                       Integer pageSize,
                                       Integer pageNumber,
                                       String[] sortBy) {
        int defaultPageSize = 10;
        String[] defaultSortBy = {"packageNo"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));


        return packageRepository.findBySentDateAndStatus(sentDate, true, pageable)
                .map(packageMapper::toDto);    }

    @Override
    public Page<PackageDto> findByFromDepartment(String name,
                                                 Integer pageSize,
                                                 Integer pageNumber,
                                                 String[] sortBy) {
        int defaultPageSize = 10;
        String[] defaultSortBy = {"fromDepartment"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return packageRepository.findByFromDepartmentAndStatus(name,true, pageable)
                .map(packageMapper::toDto);    }

    @Override
    public Page<PackageDto> findByToDepartment(String name,
                                               Integer pageSize,
                                               Integer pageNumber,
                                               String[] sortBy) {
        int defaultPageSize = 10;
        String[] defaultSortBy = {"toDepartment"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return packageRepository.findByToDepartmentAndStatus(name,true, pageable)
                .map(packageMapper::toDto);    }

    @Override
    public Page<PackageDto> findByCreatedBy(String createdBy,
                                            Integer pageSize,
                                            Integer pageNumber,
                                            String[] sortBy) {
        int defaultPageSize = 10;
        String[] defaultSortBy = {"createdBy"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return packageRepository.findByCreatedByAndStatus(createdBy, true, pageable)
                .map(packageMapper::toDto);    }

    @Override
    @Transactional
    public PackageDto save(PackageDto packageDto) {

        Package aPackage = new Package();
        aPackage.setId(null);
        aPackage.setStatus(true);
        aPackage.setUniqueId(packageRepository.findByMaxUniqueId() + 1);
        aPackage.setPackageNo(packageDto.getPackageNo());
        aPackage.setCreatedAt(null);
        aPackage.setSentDate(packageDto.getSentDate());
        aPackage.setReceiveDate(packageDto.getReceiveDate());
        aPackage.setReceiverSignature(packageDto.getReceiverSignature());
        aPackage.setSenderSignature(packageDto.getSenderSignature());

        aPackage.setForm(formRepository.findById(packageDto.getForm().getId())
                .orElseThrow(()-> new NotFoundException("Package by id : " + packageDto.getForm().getId() + " not found")));

        List<Letter> letters = new ArrayList<>();
        Letter letter;
        for (LetterDto letterDto : packageDto.getLetters()){
            letter = letterRepository.findByIdAndStatus(letterDto.getId(), true)
                    .orElseThrow(()-> new NotFoundException("Letter by id : " + letterDto.getId() + " not found"));
            letters.add(letter);
        }

        aPackage.setLetters(letters);
        aPackage.setLetterCount(aPackage.getLetters().size());

        Package save = packageRepository.save(aPackage);

        return packageMapper.toDto(save);
    }

    @Override
    @Transactional
    public PackageDto update(Integer id, PackageDto packageDto) {

        Package aPackage = packageRepository.findByIdAndStatus(id,true)
                .orElseThrow(() -> new NotFoundException("Package by id : " + id + " not found"));
        aPackage.setStatus(false);

        Package newPackage = new Package();
        newPackage.setId(null);
        newPackage.setUniqueId(aPackage.getUniqueId());
        newPackage.setStatus(true);
        newPackage.setCreatedAt(null);
        newPackage.setForm(formRepository.findById(aPackage.getForm().getId())
                .orElseThrow(() -> new NotFoundException("Form by id : " + id + " not found")));
        newPackage.setPackageNo(packageDto.getPackageNo());
        newPackage.setSentDate(packageDto.getSentDate());
        newPackage.setReceiveDate(packageDto.getReceiveDate());
        newPackage.setReceiverSignature(packageDto.getReceiverSignature());
        newPackage.setSenderSignature(packageDto.getSenderSignature());

        List<Letter> letters = new ArrayList<>();
        Letter letter;
        for (LetterDto letterDto : packageDto.getLetters()){
             letter = letterRepository.findByIdAndStatus(letterDto.getId(), true)
                     .orElseThrow(()-> new NotFoundException("Letter by id : " + letterDto.getId() + " not found"));
            letters.add(letter);
        }
        newPackage.setLetters(letters);
        newPackage.setLetterCount(newPackage.getLetters().size());

        Package save = packageRepository.save(newPackage);

        return packageMapper.toDto(save);
    }

    @Override
    public void delete(Integer id) {

    }
}
