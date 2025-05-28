package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.entities.Category;
import io.github.paulofeijo.financj.services.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController<Category, CategoryService>{

    public CategoryController(CategoryService service) {
        super(service);
    }
}
