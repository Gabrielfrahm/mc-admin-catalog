package org.example.application.category.update;

import io.vavr.API;
import io.vavr.control.Either;
import org.example.domain.category.Category;
import org.example.domain.category.CategoryGateway;
import org.example.domain.category.CategoryID;
import org.example.domain.exceptions.DomainException;
import org.example.domain.validation.Error;
import org.example.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
    final var anId = CategoryID.from(aCommand.Id());

    final var aCategory = this.categoryGateway.findById(anId)
        .orElseThrow(this.notFound(anId));

    final var notification = Notification.create();



    aCategory
        .update(aCommand.name(), aCommand.description(), aCommand.isActive()).validate(notification);
    return notification.hasError() ? Either.left(notification) : this.update(aCategory);
  }

  private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
    return API.Try(() -> this.categoryGateway.update(aCategory))
        .toEither()
        .mapLeft(Notification::create)
        .map(UpdateCategoryOutput::from);
  }

  private Supplier<DomainException> notFound(final CategoryID anId) {
    return () -> DomainException.with(new Error("Category with ID %s was not found".formatted(anId.getValue())));
  }
}
