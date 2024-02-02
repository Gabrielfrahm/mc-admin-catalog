package org.example.application.category.create;

import io.vavr.control.Either;
import org.example.application.UseCase;
import org.example.domain.validation.handler.Notification;

public abstract class CreateCategoryUseCase
  extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
