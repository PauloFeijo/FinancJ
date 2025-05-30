package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Category;
import io.github.paulofeijo.financj.entities.Entry;
import io.github.paulofeijo.financj.repositories.CategoryRepository;
import io.github.paulofeijo.financj.repositories.EntryRepository;
import org.springframework.stereotype.Service;

@Service
public class EntryService extends BaseService<Entry, EntryRepository>{

    private CategoryRepository categoryRepository;

    public EntryService(EntryRepository repository, CategoryRepository categoryRepository) {
        super(repository);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Entry create(Entry entity) {
        setTypeFromCategory(entity);
        return super.create(entity);
    }

    @Override
    public Entry update(Long id, Entry entity) {
        setTypeFromCategory(entity);
        return super.update(id, entity);
    }

    private void setTypeFromCategory(Entry entity) {
        if (entity.getCategory() == null)
            return;

        Category category = categoryRepository.findById(entity.getCategory().getId()).orElse(null);
        if (category != null) {
            entity.setType(category.getType());
            entity.setCategory(category);
        }
    }
}
