package org.example.application.category.retrive.get;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryID;

import java.time.Instant;

public record CategoryOutput(
    CategoryID id,
    String name,
    String description,
    boolean isActive,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {

  public static CategoryOutput from (final Category aCategory){
    return new CategoryOutput(
        aCategory.getId(),
        aCategory.getName(),
        aCategory.getDescription(),
        aCategory.isActive(),
        aCategory.getCreatedAt(),
        aCategory.getUpdatedAt(),
        aCategory.getDeletedAt()
    );
  }
}