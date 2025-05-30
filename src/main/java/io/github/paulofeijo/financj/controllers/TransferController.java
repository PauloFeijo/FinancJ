package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.entities.Transfer;
import io.github.paulofeijo.financj.services.TransferService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController extends BaseController<Transfer, TransferService>{

    public TransferController(TransferService service) {
        super(service);
    }
}
