package com.sa.carmanagement.service;

import com.sa.carmanagement.abstractfactory.ClassicUIFactory;
import com.sa.carmanagement.abstractfactory.ModernUIFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UIServiceTest {

    private UIService uiService;

    @BeforeEach
    void setUp() {
        uiService = new UIService(new ModernUIFactory(), new ClassicUIFactory());
    }

    @Test
    void testCreateModernUI() {
        Map<String, String> result = uiService.createUI("MODERN");

        assertNotNull(result);
        assertEquals("Modern", result.get("theme"));
        assertEquals("Modern Button", result.get("button"));
        assertEquals("Modern TextField", result.get("textField"));
    }

    @Test
    void testCreateClassicUI() {
        Map<String, String> result = uiService.createUI("CLASSIC");

        assertNotNull(result);
        assertEquals("Classic", result.get("theme"));
        assertEquals("Classic Button", result.get("button"));
        assertEquals("Classic TextField", result.get("textField"));
    }

    @Test
    void testCreateUICaseInsensitive() {
        Map<String, String> modernLower = uiService.createUI("modern");
        Map<String, String> classicMixed = uiService.createUI("Classic");

        assertEquals("Modern", modernLower.get("theme"));
        assertEquals("Classic", classicMixed.get("theme"));
    }

    @Test
    void testCreateUIInvalidTheme() {
        assertThrows(IllegalArgumentException.class, () -> {
            uiService.createUI("INVALID");
        });
    }
}
