package org.example.creational.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentFactoryTest {

    @Test
    void testCreatePdfDocument() {
        Document doc = DocumentFactory.createDocument("PDF");
        assertNotNull(doc);
        assertTrue(doc instanceof PdfDocument);
    }

    @Test
    void testCreateWordDocument() {
        Document doc = DocumentFactory.createDocument("Word");
        assertNotNull(doc);
        assertTrue(doc instanceof WordDocument);
    }

    @Test
    void testCreateHtmlDocument() {
        Document doc = DocumentFactory.createDocument("HTML");
        assertNotNull(doc);
        assertTrue(doc instanceof HtmlDocument);
    }

    @Test
    void testUnknownDocumentType() {
        assertThrows(IllegalArgumentException.class, () -> {
            DocumentFactory.createDocument("Unknown");
        });
    }

    @Test
    void testPdfDocumentOpen() {
        Document doc = new PdfDocument();
        assertDoesNotThrow(() -> doc.open());
    }

    @Test
    void testWordDocumentOpen() {
        Document doc = new WordDocument();
        assertDoesNotThrow(() -> doc.open());
    }

    @Test
    void testHtmlDocumentOpen() {
        Document doc = new HtmlDocument();
        assertDoesNotThrow(() -> doc.open());
    }
}
