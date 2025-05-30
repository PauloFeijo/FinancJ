package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Transfer;
import io.github.paulofeijo.financj.repositories.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService extends BaseService<Transfer, TransferRepository>{

    public TransferService(TransferRepository repository) {
        super(repository);
    }
}
