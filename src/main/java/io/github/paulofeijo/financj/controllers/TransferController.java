package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.controllers.dtos.InputTransferDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputTransferDto;
import io.github.paulofeijo.financj.controllers.mappers.TransferMapper;
import io.github.paulofeijo.financj.entities.Transfer;
import io.github.paulofeijo.financj.services.TransferService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController extends BaseController2<Transfer, TransferService, InputTransferDto, OutputTransferDto, TransferMapper> {

    public TransferController(TransferService service, TransferMapper mapper) {
        super(service, mapper);
    }
}
