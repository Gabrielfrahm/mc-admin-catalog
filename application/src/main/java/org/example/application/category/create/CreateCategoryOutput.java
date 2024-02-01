package org.example.application.category.create;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryID;

public record CreateCategoryOutput(
    CategoryID id
) {
  public static CreateCategoryOutput from(final Category aCategory){
    return new CreateCategoryOutput(aCategory.getId());
  }
}
