package org.example.application.category.retrive.list;

import org.example.domain.category.CategoryGateway;
import org.example.domain.category.CategorySearchQuery;
import org.example.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase  extends  ListCategoriesUseCase{

  private final CategoryGateway categoryGateway;

  public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway){
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public Pagination<CategoryListOutput> execute(CategorySearchQuery aQuery) {
    return this.categoryGateway.findAll(aQuery)
        .map(CategoryListOutput::from);
  }
}
