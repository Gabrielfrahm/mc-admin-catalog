package org.example.application.category.create;

import io.vavr.API;
import io.vavr.control.Either;
import org.example.domain.category.Category;
import org.example.domain.category.CategoryGateway;
import org.example.domain.validation.handler.Notification;
import org.example.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public Either<Notification,CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
    final var notification = Notification.create();
    final var aCategory = Category.newCategory(aCommand.name(), aCommand.description(), aCommand.isActive());

    aCategory.validate(notification);

    return notification.hasError() ? Either.left(notification) : this.create(aCategory);
  }

  private Either<Notification, CreateCategoryOutput> create(Category aCategory) {
    return API.Try(() -> this.categoryGateway.create(aCategory))
        .toEither()
        .map(CreateCategoryOutput::from)
        .mapLeft(Notification::create);
  }
}
