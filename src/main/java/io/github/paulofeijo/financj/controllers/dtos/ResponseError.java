package io.github.paulofeijo.financj.controllers.dtos;

import java.util.List;

public record ResponseError(
        int status,
        String message,
        List<FieldError> fields) {
}
