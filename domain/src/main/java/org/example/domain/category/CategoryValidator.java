package org.example.domain.category;

import org.example.domain.validation.Error;
import org.example.domain.validation.ValidationHandler;
import org.example.domain.validation.Validator;

public class CategoryValidator  extends Validator {

    private final Category category;
    public CategoryValidator(final Category category, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstrains();
    }

    private void checkNameConstrains() {
        final var name = this.category.getName();
        if(name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if(name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if(length > 255 || length < 3){
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
