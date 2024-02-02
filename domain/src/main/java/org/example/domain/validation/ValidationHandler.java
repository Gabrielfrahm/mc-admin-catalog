package org.example.domain.validation;

import java.util.List;

public interface ValidationHandler {
    // interface fluente é chamar o metodo e retornar a propria instancia e encadear
    // chamadas de metodos
    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(Validation aValidation);

    List<Error> getErrors();
    default  boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError() {
        if(getErrors() != null && !getErrors().isEmpty()){
            return getErrors().get(0);
        } else {
            return null;
        }
    }

    public  interface Validation {
        void validate();
    }
}
