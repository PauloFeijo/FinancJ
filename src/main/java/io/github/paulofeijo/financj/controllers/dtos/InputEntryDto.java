package io.github.paulofeijo.financj.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record InputEntryDto(
        @NotNull(message = "Category cannot be null")
        Long categoryId,

        @NotNull(message = "Account cannot be null")
        Long accountId,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "Date cannot be null")
        Date date,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be greater than zero")
        Double amount,

        @NotNull(message = "Description cannot be null")
        String description
) { }
