package org.example.application.category.retrive.get;

import org.example.domain.category.CategoryGateway;
import org.example.domain.category.CategoryID;
import org.example.domain.exceptions.DomainException;
import org.example.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

  private CategoryGateway categoryGateway;

  public DefaultGetCategoryByIdUseCase(CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public CategoryOutput execute(String aIn) {
    final var anCategoryID = CategoryID.from(aIn);

    return this.categoryGateway.findById(anCategoryID)
        .map(CategoryOutput::from).orElseThrow(this.notFound(anCategoryID));
  }

  private Supplier<DomainException> notFound(final CategoryID anId) {
    return () -> DomainException.with(
        new Error("Category with ID %s was not found".formatted(anId.getValue()))
    );
  }
}