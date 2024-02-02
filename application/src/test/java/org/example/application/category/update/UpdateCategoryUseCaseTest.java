package org.example.application.category.update;

import org.example.domain.category.Category;
import org.example.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

  @InjectMocks
  private DefaultUpdateCategoryUseCase useCase;

  @Mock
  private CategoryGateway categoryGateway;

  @BeforeEach
  void cleanUp(){
    Mockito.reset(categoryGateway);
  }

  @Test
  public void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId(){
    final var aCategory = Category.newCategory("fil", null, true);

    final var expectedName = "film";
    final var expectedDescription = "the category most watch";
    final var expectedIsActive = true;
    final var expectedId = aCategory.getId();

    final var aCommand = UpdateCategoryCommand.with(
        expectedId.getValue(),
        expectedName,
        expectedDescription,
        expectedIsActive
    );

    // mock find
    Mockito.when(categoryGateway.findById(Mockito.eq(expectedId)))
        .thenReturn(Optional.of(Category.with(aCategory)));
    System.out.println(aCategory);
    // mock update
    Mockito.when(categoryGateway.update(Mockito.any()))
        .thenAnswer(AdditionalAnswers.returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());


    Mockito.verify(categoryGateway, Mockito.times(1)).findById((Mockito.eq(expectedId)));

    Mockito.verify(categoryGateway, Mockito.times(1)).update(Mockito.argThat(aUpdateCategory ->
        Objects.equals(expectedName, aUpdateCategory.getName())
            && Objects.equals(expectedDescription, aUpdateCategory.getDescription())
            && Objects.equals(expectedIsActive, aUpdateCategory.isActive())
            && Objects.equals(expectedId, aUpdateCategory.getId())
            && Objects.nonNull(aCategory.getCreatedAt())
            && aCategory.getUpdatedAt().isBefore(aUpdateCategory.getUpdatedAt())
            && Objects.isNull(aCategory.getDeletedAt())
    ));
  }
}
