package org.example.application.category.update;

import io.vavr.control.Either;
import org.example.application.UseCase;
import org.example.domain.validation.handler.Notification;

public  abstract class UpdateCategoryUseCase extends
    UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
