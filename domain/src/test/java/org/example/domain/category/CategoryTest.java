package org.example.domain.category;

import org.example.domain.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

   @Test
   public void givenAValidParams_whenCallNreCategory_thenInstantiateACategory(){
       final var expectedName = "films";
       final var expectedDescription = "the most category watched";
       final var expectedIsActive = true;

       final var actualCategory =
               Category.newCategory(expectedName, expectedDescription, expectedIsActive);

       Assertions.assertNotNull(actualCategory);
       Assertions.assertNotNull(actualCategory.getId());
       Assertions.assertEquals(expectedName, actualCategory.getName());
       Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
       Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
       Assertions.assertNotNull(actualCategory.getCreatedAt());
       Assertions.assertNotNull(actualCategory.getUpdatedAt());
       Assertions.assertNull(actualCategory.getDeletedAt());
    }
}
