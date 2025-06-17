package io.github.paulofeijo.financj.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public record InputAccountDto (
        @NotBlank(message = "Description cannot be blank")
        String description,
        String number
){}