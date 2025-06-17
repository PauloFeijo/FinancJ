package io.github.paulofeijo.financj.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InputCategoryDto(
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotNull(message = "Parent cannot be null")
        Long parentId
){}