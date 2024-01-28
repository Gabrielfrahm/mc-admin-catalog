package org.example.domain;

import org.example.domain.validation.ValidationHandler;

public abstract class AggregateRoot<ID extends  Identifier> extends Entity<ID>{
    protected AggregateRoot(final ID id) {
        super(id);
    }
}
