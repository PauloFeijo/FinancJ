package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.controllers.mappers.BaseMapper;
import io.github.paulofeijo.financj.entities.EntityBase;
import io.github.paulofeijo.financj.services.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public abstract class BaseController2<
        Entity extends EntityBase,
        Service extends BaseService<Entity, ?>,
        InputDto,
        OutputDto,
        Mapper extends BaseMapper<Entity, InputDto, OutputDto>> {

    protected final Service service;
    private final Mapper mapper;

    protected BaseController2(Service service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<OutputDto>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputDto> findById(@PathVariable Long id) {
        Entity entity = service.getById(id);
        return (entity == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(mapper.toDto(entity));
    }

    @PostMapping
    protected ResponseEntity<OutputDto> create(@RequestBody @Valid InputDto dto) {
        Entity created = service.create(mapper.toEntity(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(mapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputDto> update(@PathVariable Long id, @RequestBody @Valid InputDto dto) {
        Entity updated = service.update(id, mapper.toEntity(dto));
        return (updated == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.accepted().body(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}