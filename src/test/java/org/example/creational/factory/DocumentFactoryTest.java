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

    @Test
    void testPdfDocumentSave() {
        Document doc = new PdfDocument();
        assertDoesNotThrow(() -> doc.save());
    }

    @Test
    void testWordDocumentSave() {
        Document doc = new WordDocument();
        assertDoesNotThrow(() -> doc.save());
    }

    @Test
    void testHtmlDocumentSave() {
        Document doc = new HtmlDocument();
        assertDoesNotThrow(() -> doc.save());
    }

    @Test
    void testPdfDocumentSetAndGetContent() {
        Document doc = new PdfDocument();
        doc.setContent("Test PDF content");
        assertEquals("Test PDF content", doc.getContent());
    }

    @Test
    void testWordDocumentSetAndGetContent() {
        Document doc = new WordDocument();
        doc.setContent("Test Word content");
        assertEquals("Test Word content", doc.getContent());
    }

    @Test
    void testHtmlDocumentSetAndGetContent() {
        Document doc = new HtmlDocument();
        doc.setContent("Test HTML content");
        assertEquals("Test HTML content", doc.getContent());
    }

    @Test
    void testPdfDocumentDisplay() {
        Document doc = new PdfDocument();
        doc.setContent("PDF content");
        assertDoesNotThrow(() -> doc.display());
    }

    @Test
    void testWordDocumentDisplay() {
        Document doc = new WordDocument();
        doc.setContent("Word content");
        assertDoesNotThrow(() -> doc.display());
    }

    @Test
    void testHtmlDocumentDisplay() {
        Document doc = new HtmlDocument();
        doc.setContent("HTML content");
        assertDoesNotThrow(() -> doc.display());
    }

    @Test
    void testDocumentDefaultContent() {
        Document pdfDoc = new PdfDocument();
        Document wordDoc = new WordDocument();
        Document htmlDoc = new HtmlDocument();

        assertEquals("", pdfDoc.getContent());
        assertEquals("", wordDoc.getContent());
        assertEquals("", htmlDoc.getContent());
    }
}
