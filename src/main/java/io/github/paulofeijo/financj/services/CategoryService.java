package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Category;
import io.github.paulofeijo.financj.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository>{

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
