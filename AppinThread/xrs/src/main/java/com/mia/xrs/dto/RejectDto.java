package com.mia.xrs.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RejectDto {

    private Integer id;

    private Integer uniqueId;

    private String routeNo;

    private Date returnDate;

    private LetterDto letter;

    private String rejectReason;

    private UserDto returner;

    private UserDto receiver;

    private String returnerSignature;

    private String receiverSignature;
}
