package org.example.creational;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentEditorTest {

    @Test
    void testOpenPdfDocument() {
        DocumentEditor editor = new DocumentEditor();
        assertDoesNotThrow(() -> editor.openDocument("PDF"));
    }

    @Test
    void testOpenWordDocument() {
        DocumentEditor editor = new DocumentEditor();
        assertDoesNotThrow(() -> editor.openDocument("Word"));
    }

    @Test
    void testOpenHtmlDocument() {
        DocumentEditor editor = new DocumentEditor();
        assertDoesNotThrow(() -> editor.openDocument("HTML"));
    }

    @Test
    void testOpenUnknownDocument() {
        DocumentEditor editor = new DocumentEditor();
        assertThrows(IllegalArgumentException.class, () -> editor.openDocument("Unknown"));
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> DocumentEditor.main(new String[]{}));
    }
}
