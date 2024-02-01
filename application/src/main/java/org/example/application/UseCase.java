package org.example.application;

import org.example.domain.category.Category;

public  abstract  class UseCase<IN, OUT> {
  public abstract OUT execute(IN anIn);
}