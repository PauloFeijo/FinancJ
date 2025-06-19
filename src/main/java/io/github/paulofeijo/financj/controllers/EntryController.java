package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.controllers.dtos.InputEntryDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputEntryDto;
import io.github.paulofeijo.financj.controllers.mappers.EntryMapper;
import io.github.paulofeijo.financj.entities.Entry;
import io.github.paulofeijo.financj.services.EntryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/entries")
public class EntryController extends BaseController2<Entry, EntryService, InputEntryDto, OutputEntryDto, EntryMapper> {

    protected EntryController(EntryService service, EntryMapper mapper) {
        super(service, mapper);
    }
}