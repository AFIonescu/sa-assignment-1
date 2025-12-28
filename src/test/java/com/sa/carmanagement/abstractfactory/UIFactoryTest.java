package com.sa.carmanagement.abstractfactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UIFactoryTest {

    @Test
    void testModernUIFactory() {
        UIFactory factory = new ModernUIFactory();

        Button button = factory.createButton();
        TextField textField = factory.createTextField();

        assertNotNull(button);
        assertNotNull(textField);
        assertTrue(button instanceof ModernButton);
        assertTrue(textField instanceof ModernTextField);
        assertEquals("Modern", button.getStyle());
        assertEquals("Modern", textField.getStyle());
        assertEquals("Modern", factory.getTheme());
    }

    @Test
    void testClassicUIFactory() {
        UIFactory factory = new ClassicUIFactory();

        Button button = factory.createButton();
        TextField textField = factory.createTextField();

        assertNotNull(button);
        assertNotNull(textField);
        assertTrue(button instanceof ClassicButton);
        assertTrue(textField instanceof ClassicTextField);
        assertEquals("Classic", button.getStyle());
        assertEquals("Classic", textField.getStyle());
        assertEquals("Classic", factory.getTheme());
    }

    @Test
    void testModernButtonPaint() {
        Button button = new ModernButton();
        assertDoesNotThrow(button::paint);
        assertEquals("Modern", button.getStyle());
    }

    @Test
    void testClassicButtonPaint() {
        Button button = new ClassicButton();
        assertDoesNotThrow(button::paint);
        assertEquals("Classic", button.getStyle());
    }

    @Test
    void testModernTextFieldRender() {
        TextField textField = new ModernTextField();
        assertDoesNotThrow(textField::render);
        assertEquals("Modern", textField.getStyle());
    }

    @Test
    void testClassicTextFieldRender() {
        TextField textField = new ClassicTextField();
        assertDoesNotThrow(textField::render);
        assertEquals("Classic", textField.getStyle());
    }

    @Test
    void testFactoryConsistency() {
        UIFactory modernFactory = new ModernUIFactory();
        Button modernButton1 = modernFactory.createButton();
        Button modernButton2 = modernFactory.createButton();

        assertNotSame(modernButton1, modernButton2);
        assertEquals(modernButton1.getStyle(), modernButton2.getStyle());
    }
}
