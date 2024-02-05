package org.example.application.category.retrive.list;

import org.example.application.UseCase;
import org.example.domain.category.CategorySearchQuery;
import org.example.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
    extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
