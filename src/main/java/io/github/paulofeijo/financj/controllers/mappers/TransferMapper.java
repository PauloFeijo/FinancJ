package io.github.paulofeijo.financj.controllers.mappers;

import io.github.paulofeijo.financj.controllers.dtos.InputTransferDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputTransferDto;
import io.github.paulofeijo.financj.entities.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper  extends BaseMapper<Transfer, InputTransferDto, OutputTransferDto> {
    @Override
    @Mapping(source = "accountDebitId", target = "accountDebit.id")
    @Mapping(source = "accountCreditId", target = "accountCredit.id")
    Transfer toEntity(InputTransferDto dto);

    @Override
    @Mapping(source = "accountDebit.id", target = "accountDebitId")
    @Mapping(source = "accountCredit.id", target = "accountCreditId")
    OutputTransferDto toDto(Transfer entity);
}