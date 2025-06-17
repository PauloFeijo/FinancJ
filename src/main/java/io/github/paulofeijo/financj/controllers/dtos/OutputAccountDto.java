package io.github.paulofeijo.financj.controllers.dtos;

public record OutputAccountDto (
    Long id,
    String description,
    String number,
    Double balance
){}
