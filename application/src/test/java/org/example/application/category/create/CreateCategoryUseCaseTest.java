package org.example.application.category.create;

import org.example.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import java.util.Objects;

public class CreateCategoryUseCaseTest {

  @Test
  public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
    final var expectedName = "film";
    final var expectedDescription = "the category most watch";
    final var expectedIsActive = true;

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription,expectedIsActive);

    final CategoryGateway categoryGateway = Mockito.mock(CategoryGateway.class);
    Mockito.when(categoryGateway.create(Mockito.any()))
        .thenAnswer(AdditionalAnswers.returnsFirstArg());

    final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);

    final var actualOutput = useCase.execute(aCommand);

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(categoryGateway, Mockito.times(1))
        .create(Mockito.argThat(aCategory -> {
              return Objects.equals(expectedName, aCategory.getName()) &&
                  Objects.equals(expectedDescription, aCategory.getDescription()) &&
                  Objects.equals(expectedIsActive, aCategory.isActive()) &&
                  Objects.nonNull(aCategory.getId()) &&
                  Objects.nonNull(aCategory.getCreatedAt()) &&
                  Objects.nonNull(aCategory.getUpdatedAt()) &&
                  Objects.isNull(aCategory.getDeletedAt());
            }
        ));
  }
}
