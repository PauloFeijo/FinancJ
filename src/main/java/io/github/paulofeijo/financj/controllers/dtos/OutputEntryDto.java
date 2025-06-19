package io.github.paulofeijo.financj.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.paulofeijo.financj.enums.Type;

import java.util.Date;

public record OutputEntryDto(
        Long id,
        Long categoryId,
        Long accountId,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date date,
        Double amount,
        String description,
        Type type
) { }
