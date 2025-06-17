package io.github.paulofeijo.financj.controllers.mappers;

import io.github.paulofeijo.financj.controllers.dtos.InputAccountDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputAccountDto;
import io.github.paulofeijo.financj.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseMapper<Account, InputAccountDto, OutputAccountDto> {
}
