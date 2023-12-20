package com.mia.xrs.service.Impl;

import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.dto.RejectDto;
import com.mia.xrs.entity.Letter;
import com.mia.xrs.entity.Reject;
import com.mia.xrs.entity.User;
import com.mia.xrs.exception.NotFoundException;
import com.mia.xrs.mapper.impl.RejectMapper;
import com.mia.xrs.repository.LetterRepository;
import com.mia.xrs.repository.RejectRepository;
import com.mia.xrs.repository.UserRepository;
import com.mia.xrs.service.RejectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RejectServiceImpl implements RejectService {

    private final RejectMapper rejectMapper;
    private final UserRepository userRepository;
    private final LetterRepository letterRepository;
    private final RejectRepository rejectRepository;

    @Override
    public Page<RejectDto> findAllPage(Integer pageSize, Integer pageNumber, String[] sortBy) {

        int defaultPageSize = 10;
        String[] defaultSortBy = {"rejectNo"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return rejectRepository.findByStatus(true, pageable)
                .map(rejectMapper::toDto);
    }

    @Override
    public RejectDto findById(Integer id) {

        Reject reject = rejectRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new NotFoundException("Reject by id : " + id + " not found"));

        return rejectMapper.toDto(reject);
    }

    @Override
    public RejectDto findByRouteNo(String routeNo) {
        Reject reject = rejectRepository.findByRouteNoAndStatus(routeNo, true);

        return rejectMapper.toDto(reject);
    }

    @Override
    public Page<RejectDto> findByReturnDate(Date date,
                                            Integer pageSize,
                                            Integer pageNumber,
                                            String[] sortBy) {
        int defaultPageSize = 10;
        String[] defaultSortBy = {"rejectNo"};

        pageSize = (pageSize == null) ? defaultPageSize : pageSize;
        sortBy = (sortBy == null) ? defaultSortBy : sortBy;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));

        return rejectRepository.findByReturnDate(date, pageable, true)
                .map(rejectMapper::toDto);
    }

    @Override
    public RejectDto findByLetterNo(Integer letterNo, Boolean status) {

        Reject reject = rejectRepository.findByLetterNoAndRejectStatus(letterNo, status)
                .orElseThrow(()-> new NotFoundException("Letter by no : " + letterNo + " not found"));

        return rejectMapper.toDto(reject);
    }



    @Override
    @Transactional
    public RejectDto save(RejectDto rejectDto) {

        Reject reject = new Reject();
        reject.setId(null);
        reject.setStatus(true);
        reject.setUniqueId(rejectRepository.findByMaxUniqueId() + 1);
        reject.setRouteNo(rejectDto.getRouteNo());
        reject.setReturnDate(rejectDto.getReturnDate());
        reject.setCreatedAt(null);

        User receive = userRepository.findById(rejectDto.getReceiver().getId())
                .orElseThrow(() -> new NotFoundException("Receiver by id: " + rejectDto.getReceiver().getId() + "not found"));

        User returner = userRepository.findById(rejectDto.getReturner().getId())
                .orElseThrow(() -> new NotFoundException("Returner by id: " + rejectDto.getReturner().getId() + "not found"));

        Letter letter = letterRepository.findById(rejectDto.getLetter().getId())
                .orElseThrow(() -> new NotFoundException("Letter by id: " + rejectDto.getLetter().getId() + "not found"));

        reject.setLetter(letter);
        reject.setReturner(returner);
        reject.setReceiver(receive);
        reject.setRejectReason(rejectDto.getRejectReason());
        reject.setReceiverSignature(rejectDto.getReceiverSignature());
        reject.setReturnerSignature(rejectDto.getReturnerSignature());

        Reject save = rejectRepository.save(reject);
        return rejectMapper.toDto(save);
    }

    @Override
    @Transactional
    public RejectDto update(Integer id, RejectDto rejectDto) {

        Reject oldReject = rejectRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new NotFoundException("Reject by id: " + id + "not found"));
        oldReject.setStatus(false);

        Reject newReject = new Reject();
        newReject.setId(null);
        newReject.setStatus(true);
        newReject.setUniqueId(oldReject.getUniqueId());
        newReject.setCreatedAt(null);
        newReject.setRouteNo(rejectDto.getRouteNo());
        newReject.setRejectReason(rejectDto.getRejectReason());

        User receive = userRepository.findById(rejectDto.getReceiver().getId())
                .orElseThrow(() -> new NotFoundException("Receiver by id: " + rejectDto.getReceiver().getId() + "not found"));

        User returner = userRepository.findById(rejectDto.getReturner().getId())
                .orElseThrow(() -> new NotFoundException("Returner by id: " + rejectDto.getReturner().getId() + "not found"));

        Letter letter = letterRepository.findById(rejectDto.getLetter().getId())
                .orElseThrow(() -> new NotFoundException("Letter by id: " + rejectDto.getLetter().getId() + "not found"));

        newReject.setReturner(returner);
        newReject.setReceiver(receive);
        newReject.setLetter(letter);
        newReject.setReturnerSignature(rejectDto.getReturnerSignature());
        newReject.setReceiverSignature(rejectDto.getReceiverSignature());
        newReject.setReturnDate(rejectDto.getReturnDate());

        Reject save = rejectRepository.save(newReject);

        return rejectMapper.toDto(newReject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        Reject reject = rejectRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new NotFoundException("Reject by id : " + id + " not found"));

        reject.setStatus(false);
        rejectRepository.save(reject);
    }
}
