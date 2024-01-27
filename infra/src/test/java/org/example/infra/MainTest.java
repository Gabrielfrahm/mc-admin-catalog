package org.example.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public  void TestMain(){
        Assertions.assertNotNull(new Main());
        Main.main(new String[]{});
    }
}
