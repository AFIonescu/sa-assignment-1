package org.example.creational;

import org.example.creational.factory.Document;
import org.example.creational.factory.PdfDocument;
import org.example.creational.factory.WordDocument;
import org.example.creational.factory.HtmlDocument;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentEditorTest {

    @Test
    void testCreatePdfDocument() {
        DocumentEditor editor = new DocumentEditor();
        Document doc = editor.createDocument("PDF");
        assertNotNull(doc);
        assertTrue(doc instanceof PdfDocument);
    }

    @Test
    void testCreateWordDocument() {
        DocumentEditor editor = new DocumentEditor();
        Document doc = editor.createDocument("Word");
        assertNotNull(doc);
        assertTrue(doc instanceof WordDocument);
    }

    @Test
    void testCreateHtmlDocument() {
        DocumentEditor editor = new DocumentEditor();
        Document doc = editor.createDocument("HTML");
        assertNotNull(doc);
        assertTrue(doc instanceof HtmlDocument);
    }

    @Test
    void testCreateDocumentWithContent() {
        DocumentEditor editor = new DocumentEditor();
        Document doc = editor.createDocument("PDF", "Test content");
        assertNotNull(doc);
        assertEquals("Test content", doc.getContent());
    }

    @Test
    void testCreateWordDocumentWithContent() {
        DocumentEditor editor = new DocumentEditor();
        Document doc = editor.createDocument("Word", "Word content");
        assertNotNull(doc);
        assertEquals("Word content", doc.getContent());
    }

    @Test
    void testCreateHtmlDocumentWithContent() {
        DocumentEditor editor = new DocumentEditor();
        Document doc = editor.createDocument("HTML", "HTML content");
        assertNotNull(doc);
        assertEquals("HTML content", doc.getContent());
    }

    @Test
    void testCreateUnknownDocument() {
        DocumentEditor editor = new DocumentEditor();
        assertThrows(IllegalArgumentException.class, () -> editor.createDocument("Unknown"));
    }

    @Test
    void testCreateUnknownDocumentWithContent() {
        DocumentEditor editor = new DocumentEditor();
        assertThrows(IllegalArgumentException.class, () -> editor.createDocument("Unknown", "content"));
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> DocumentEditor.main(new String[]{}));
    }
}
