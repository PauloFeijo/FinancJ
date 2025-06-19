package io.github.paulofeijo.financj.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record OutputTransferDto(
        Long id,
        String username,
        Long accountDebitId,
        Long accountCreditId,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date date,
        Double amount
) {}