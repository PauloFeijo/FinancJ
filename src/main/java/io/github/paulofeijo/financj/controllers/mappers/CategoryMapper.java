package io.github.paulofeijo.financj.controllers.mappers;

import io.github.paulofeijo.financj.controllers.dtos.InputCategoryDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputCategoryDto;
import io.github.paulofeijo.financj.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, InputCategoryDto, OutputCategoryDto> {
    @Override
    @Mapping(source = "parentId", target = "parent.id")
    Category toEntity(InputCategoryDto dto);

    @Override
    @Mapping(source = "parent.id", target = "parentId")
    OutputCategoryDto toDto(Category entity);
}
