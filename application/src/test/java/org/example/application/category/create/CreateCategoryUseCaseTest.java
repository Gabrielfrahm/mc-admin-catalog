package org.example.application.category.create;

import org.example.domain.category.CategoryGateway;
import org.example.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

  @InjectMocks
  private DefaultCreateCategoryUseCase useCase;

  @Mock
  private CategoryGateway categoryGateway;

  @Test
  public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
    final var expectedName = "film";
    final var expectedDescription = "the category most watch";
    final var expectedIsActive = true;

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

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

  @Test
  public void givenAInvalidName_whenCallsCreateCategory_thenShouldReturnDomainException() {
    final String expectedName = null;
    final var expectedDescription = "the category most watch";
    final var expectedIsActive = true;
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

    final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));
    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

    Mockito.verify(categoryGateway, Mockito.times(0)).create(Mockito.any());
  }

  @Test
  public void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnInactiveCategoryId() {
    final var expectedName = "film";
    final var expectedDescription = "the category most watch";
    final var expectedIsActive = false;

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

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
                  Objects.nonNull(aCategory.getDeletedAt());
            }
        ));
  }

  @Test
  public void givenAValidCommand_whenGatewayThrowsRandomException_ShouldReturnAException(){
    final var expectedName = "film";
    final var expectedDescription = "the category most watch";
    final var expectedIsActive = true;
    final var expectedErrorMessage = "Gateway error";

    final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

    Mockito.when(categoryGateway.create(Mockito.any()))
        .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aCommand));

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

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
