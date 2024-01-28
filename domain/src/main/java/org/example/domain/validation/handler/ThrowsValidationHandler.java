package org.example.domain.validation.handler;

import org.example.domain.exceptions.DomainException;
import org.example.domain.validation.Error;
import org.example.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(List.of(anError));
    }

    @Override
    public ValidationHandler append(ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
       try {
           aValidation.validate();
       }catch (final Exception ex) {
           throw  DomainException.with(List.of(new Error(ex.getMessage())));
       }

       return  this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
