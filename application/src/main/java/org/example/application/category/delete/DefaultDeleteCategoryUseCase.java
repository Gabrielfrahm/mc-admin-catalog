package org.example.application.category.delete;

import org.example.domain.category.CategoryGateway;
import org.example.domain.category.CategoryID;

import java.util.Objects;

public class DefaultDeleteCategoryUseCase extends  DeleteCategoryUseCase {

  private CategoryGateway categoryGateway;

  public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway){
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }
  @Override
  public void execute(final String anId) {
    this.categoryGateway.deleteById(CategoryID.from(anId));
  }
}
