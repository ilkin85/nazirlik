package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.RejectDto;
import com.mia.xrs.dto.UserDto;
import com.mia.xrs.entity.Reject;
import com.mia.xrs.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RejectMapper implements Mapper<Reject, RejectDto> {

    private final LetterMapper letterMapper;

    @Override
    public RejectDto toDto(Reject reject) {

        return RejectDto.builder()
                .id(reject.getId())
                .routeNo(reject.getRouteNo())
                .returnDate(reject.getReturnDate())
                .letter(letterMapper.toDto(reject.getLetter()))
                .rejectReason(reject.getRejectReason())
                .returner(UserDto.builder()
                        .firstName(reject.getReturner().getUserSpecification().getFirstName())
                        .lastName(reject.getReturner().getUserSpecification().getLastName())
                        .fatherName(reject.getReturner().getUserSpecification().getLastName())
                        .rank(reject.getReturner().getUserSpecification().getRank())
                        .build())
                .receiver(UserDto.builder()
                        .firstName(reject.getReceiver().getUserSpecification().getFirstName())
                        .lastName(reject.getReceiver().getUserSpecification().getLastName())
                        .fatherName(reject.getReceiver().getUserSpecification().getLastName())
                        .rank(reject.getReceiver().getUserSpecification().getRank())
                        .build())
                .returnerSignature(reject.getReturnerSignature())
                .receiverSignature(reject.getReceiverSignature())
                .build();
    }

    @Override
    public Reject toEntity(RejectDto rejectDto) {
        return null;
    }
}