package org.example.domain.exceptions;

import org.example.domain.validation.Error;

import java.util.List;

// error do tipo check é obrigado a ter um try catch.
// error do tipo run time acontece no em tempo de execução.
public class DomainException extends NoStacktraceException {
    private final List<Error> errors;
    private DomainException(final String aMassage, final List<Error> anErrors) {
        super(aMassage);
        this.errors = anErrors;
    }

    // factory method
    public  static DomainException with(final List<Error> anError) {
        return new DomainException("", anError);
    }

    public  static DomainException with(final Error anError) {
        return new DomainException(anError.Message(), List.of(anError));
    }

    public List<Error> getErrors() {
        return errors;
    }
}
