package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Category;
import io.github.paulofeijo.financj.entities.Entry;
import io.github.paulofeijo.financj.repositories.CategoryRepository;
import io.github.paulofeijo.financj.repositories.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EntryService extends BaseService<Entry, EntryRepository>{

    private final CategoryRepository categoryRepository;
    private final BalanceService balanceService;

    public EntryService(EntryRepository repository, CategoryRepository categoryRepository, BalanceService balanceService) {
        super(repository);
        this.categoryRepository = categoryRepository;
        this.balanceService = balanceService;
    }

    @Override
    public Entry create(Entry entry) {
        setTypeFromCategory(entry);
        var result = super.create(entry);
        balanceService.process(entry.getAccount().getId());
        return result;
    }

    @Override
    public Entry update(Long id, Entry entry) {
        setTypeFromCategory(entry);

        var oldAccountId = repository.getAccountIdByEntryId(entry.getId()).orElse(0L);
        var result = super.update(id, entry);
        var newAccountId = entry.getAccount().getId();

        balanceService.process(Arrays.asList(oldAccountId, newAccountId));
        return result;
    }

    @Override
    public void delete(Long id) {
        var accountId = repository.getAccountIdByEntryId(id).orElse(0L);
        super.delete(id);
        balanceService.process(accountId);
    }

    private void setTypeFromCategory(Entry entry) {
        if (entry.getCategory() == null)
            return;

        Category category = categoryRepository.findById(entry.getCategory().getId()).orElse(null);
        if (category != null) {
            entry.setType(category.getType());
            entry.setCategory(category);
        }
    }
}
