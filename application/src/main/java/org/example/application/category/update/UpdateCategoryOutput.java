package org.example.application.category.update;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryID;

public record UpdateCategoryOutput(
    CategoryID id
) {
  public static UpdateCategoryOutput from (final Category aCategory) {
    return new UpdateCategoryOutput(aCategory.getId());
  }
}
