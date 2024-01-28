package org.example.domain.exceptions;

import org.example.domain.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException {
    private final List<Error> errors;
    private DomainException(final List<Error> anErrors) {
        super("", null, true, false);
        this.errors = anErrors;
    }

    // factory method
    public  static DomainException with(final List<Error> anError) {
        return new DomainException(anError);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
