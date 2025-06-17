package io.github.paulofeijo.financj.controllers.dtos;

import io.github.paulofeijo.financj.enums.Type;

public record OutputCategoryDto(
    Long id,
    String description,
    Type type,
    Long parentId
){}
