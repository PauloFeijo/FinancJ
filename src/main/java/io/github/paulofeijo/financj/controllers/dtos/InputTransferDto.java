package io.github.paulofeijo.financj.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record InputTransferDto(
        @NotNull(message = "Account Debit cannot be null")
        Long accountDebitId,

        @NotNull(message = "Account Credit cannot be null")
        Long accountCreditId,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Date cannot be null")
        Date date,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be greater than zero")
        Double amount
) {}