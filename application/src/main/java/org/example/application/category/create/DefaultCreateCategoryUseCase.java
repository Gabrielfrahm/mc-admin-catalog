package org.example.application.category.create;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryGateway;
import org.example.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
    final var aCategory = Category.newCategory(aCommand.name(), aCommand.description(), aCommand.isActive());

    aCategory.validate(new ThrowsValidationHandler());

    return CreateCategoryOutput.from(this.categoryGateway.create(aCategory));
  }
}
