package io.github.paulofeijo.financj.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EntityBase {
    protected Long id;
    protected String username;
}
