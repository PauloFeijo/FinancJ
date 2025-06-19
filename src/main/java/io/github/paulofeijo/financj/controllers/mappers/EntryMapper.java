package io.github.paulofeijo.financj.controllers.mappers;

import io.github.paulofeijo.financj.controllers.dtos.InputEntryDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputEntryDto;
import io.github.paulofeijo.financj.entities.Entry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntryMapper extends BaseMapper<Entry, InputEntryDto, OutputEntryDto> {
    @Override
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(source = "categoryId", target = "category.id")
    Entry toEntity(InputEntryDto dto);

    @Override
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "category.id", target = "categoryId")
    OutputEntryDto toDto(Entry entity);
}
