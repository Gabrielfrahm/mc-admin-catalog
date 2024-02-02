package org.example.domain.validation.handler;

import org.example.domain.exceptions.DomainException;
import org.example.domain.validation.Error;
import org.example.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

  private final List<Error> errors;

  private Notification(final List<Error> errors) {
    this.errors = errors;
  }

  public static Notification create(){
    return new Notification(new ArrayList<>());
  }

  public static Notification create(final Throwable t){
    return create(new Error(t.getMessage()));
  }

  // facilitador
  public  static Notification create(final Error anError) {
    return new Notification(new ArrayList<>()).append(anError);
  }

  @Override
  public Notification append(final Error anError) {
    this.errors.add(anError);
    return this;
  }

  @Override
  public Notification append(final ValidationHandler anHandler) {
    this.errors.addAll(anHandler.getErrors());
    return this;
  }

  @Override
  public Notification validate(final Validation aValidation) {
    try {
      aValidation.validate();
    }catch (final DomainException ex) {
        this.errors.addAll(ex.getErrors());
    }catch (final Throwable t){
      this.errors.add(new Error(t.getMessage()));
    }
    return this;
  }

  @Override
  public List<Error> getErrors() {
    return this.errors;
  }
}
