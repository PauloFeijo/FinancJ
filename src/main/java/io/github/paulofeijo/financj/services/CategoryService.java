package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Category;
import io.github.paulofeijo.financj.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository>{

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public Category create(Category entity) {
        setTypeFromParentIfNeeded(entity);
        return super.create(entity);
    }

    @Override
    public Category update(Long id, Category entity) {
        setTypeFromParentIfNeeded(entity);
        return super.update(id, entity);
    }

    private void setTypeFromParentIfNeeded(Category entity) {
        if (entity.getParent() == null)
            return;

        Category parent = repository.findById(entity.getParent().getId()).orElse(null);
        if (parent != null) {
            entity.setType(parent.getType());
            entity.setParent(parent);
        }
    }
}
