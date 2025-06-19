package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.controllers.dtos.InputCategoryDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputCategoryDto;
import io.github.paulofeijo.financj.controllers.mappers.CategoryMapper;
import io.github.paulofeijo.financj.entities.Category;
import io.github.paulofeijo.financj.services.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController<Category, CategoryService, InputCategoryDto, OutputCategoryDto, CategoryMapper> {

    public CategoryController(CategoryService service, CategoryMapper mapper) {
        super(service, mapper);
    }
}
