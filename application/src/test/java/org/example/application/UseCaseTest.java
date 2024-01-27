package org.example.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UseCaseTest {

    @Test
    public void TestCreateUseCase() {
        Assertions.assertNotNull(new UseCaseTest());
        Assertions.assertNotNull(new UseCase().execute());
    }
}
