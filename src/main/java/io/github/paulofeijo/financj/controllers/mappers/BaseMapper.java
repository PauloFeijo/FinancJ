package io.github.paulofeijo.financj.controllers.mappers;

public interface BaseMapper<Entity, InputDto, OutputDto> {
    Entity toEntity(InputDto dto);
    OutputDto toDto(Entity entity);
}
