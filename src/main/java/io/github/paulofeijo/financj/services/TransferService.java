package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Transfer;
import io.github.paulofeijo.financj.repositories.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TransferService extends BaseService<Transfer, TransferRepository>{

    private final BalanceService balanceService;

    public TransferService(TransferRepository repository, BalanceService balanceService) {
        super(repository);
        this.balanceService = balanceService;
    }

    @Override
    public Transfer create(Transfer transfer) {
        var result = super.create(transfer);
        balanceService.process(Arrays.asList(transfer.getAccountCredit().getId(), transfer.getAccountDebit().getId()));
        return result;
    }

    @Override
    public Transfer update(Long id, Transfer transfer) {
        var actual = super.getById(id);

        var result = super.update(id, transfer);

        balanceService.process(Arrays.asList(
            actual.getAccountCredit().getId(),
            actual.getAccountDebit().getId(),
            transfer.getAccountCredit().getId(),
            transfer.getAccountDebit().getId()
        ));

        return result;
    }

    @Override
    public void delete(Long id) {
        var transfer = super.getById(id);

        super.delete(id);

        balanceService.process(Arrays.asList(
            transfer.getAccountCredit().getId(),
            transfer.getAccountDebit().getId()
        ));
    }
}
