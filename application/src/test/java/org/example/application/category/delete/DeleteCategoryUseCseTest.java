package org.example.application.category.delete;


import org.example.domain.category.Category;
import org.example.domain.category.CategoryGateway;
import org.example.domain.category.CategoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCseTest {

  @InjectMocks
  private DefaultDeleteCategoryUseCase useCase;

  @Mock
  private CategoryGateway categoryGateway;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(categoryGateway);
  }

  @Test
  public void givenAValidId_whenCallDeleteCategory_shouldBeOk() {
    final var aCategory = Category.newCategory("films", "The most", true);
    final var expectedId = aCategory.getId();

    Mockito.doNothing().when(categoryGateway).deleteById(Mockito.eq(expectedId));

    Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

    Mockito.verify(categoryGateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
  }

  @Test
  public void givenAInvalidId_whenCallDeleteCategory_shouldBeOk() {
    final var expectedId = CategoryID.from("123");

    Mockito.doNothing().when(categoryGateway).deleteById(Mockito.eq(expectedId));

    Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

    Mockito.verify(categoryGateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
  }

  @Test
  public void givenAInvalidId_whenGatewayThrowsException_shouldReturnException() {
    final var aCategory = Category.newCategory("films", "The most", true);
    final var expectedId = aCategory.getId();

    Mockito.doThrow(new IllegalStateException()).when(categoryGateway).deleteById(Mockito.eq(expectedId));

    Assertions.assertThrows(IllegalStateException.class,() -> useCase.execute(expectedId.getValue()));

    Mockito.verify(categoryGateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
  }
}