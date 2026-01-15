package org.example.creational;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarConfigurationTest {

    @Test
    void testMain() {
        assertDoesNotThrow(() -> CarConfiguration.main(new String[]{}));
    }
}
