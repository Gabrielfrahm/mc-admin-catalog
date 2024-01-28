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
        if(this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
