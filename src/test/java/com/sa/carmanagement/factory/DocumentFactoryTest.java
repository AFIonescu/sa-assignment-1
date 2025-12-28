package com.sa.carmanagement.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentFactoryTest {

    private DocumentFactory documentFactory;

    @BeforeEach
    void setUp() {
        documentFactory = new DocumentFactory();
    }

    @Test
    void testCreatePdfDocument() {
        Document document = documentFactory.createDocument("PDF");
        assertNotNull(document);
        assertTrue(document instanceof PdfDocument);
        assertEquals("PDF", document.getFormat());
    }

    @Test
    void testCreateWordDocument() {
        Document document = documentFactory.createDocument("WORD");
        assertNotNull(document);
        assertTrue(document instanceof WordDocument);
        assertEquals("DOCX", document.getFormat());
    }

    @Test
    void testCreateWordDocumentWithDocxType() {
        Document document = documentFactory.createDocument("DOCX");
        assertNotNull(document);
        assertTrue(document instanceof WordDocument);
        assertEquals("DOCX", document.getFormat());
    }

    @Test
    void testCreateHtmlDocument() {
        Document document = documentFactory.createDocument("HTML");
        assertNotNull(document);
        assertTrue(document instanceof HtmlDocument);
        assertEquals("HTML", document.getFormat());
    }

    @Test
    void testCreateDocumentWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> {
            documentFactory.createDocument("INVALID");
        });
    }

    @Test
    void testCreateDocumentCaseInsensitive() {
        Document pdfLower = documentFactory.createDocument("pdf");
        Document pdfUpper = documentFactory.createDocument("PDF");
        Document pdfMixed = documentFactory.createDocument("Pdf");

        assertEquals("PDF", pdfLower.getFormat());
        assertEquals("PDF", pdfUpper.getFormat());
        assertEquals("PDF", pdfMixed.getFormat());
    }

    @Test
    void testPdfDocumentSaveAndDisplay() {
        Document document = documentFactory.createDocument("PDF");
        document.save("Test PDF content");

        String displayed = document.display();
        assertTrue(displayed.contains("PDF Document"));
        assertTrue(displayed.contains("Test PDF content"));
    }

    @Test
    void testWordDocumentSaveAndDisplay() {
        Document document = documentFactory.createDocument("WORD");
        document.save("Test Word content");

        String displayed = document.display();
        assertTrue(displayed.contains("Word Document"));
        assertTrue(displayed.contains("Test Word content"));
    }

    @Test
    void testHtmlDocumentSaveAndDisplay() {
        Document document = documentFactory.createDocument("HTML");
        document.save("Test HTML content");

        String displayed = document.display();
        assertTrue(displayed.contains("<html>"));
        assertTrue(displayed.contains("<body>"));
        assertTrue(displayed.contains("Test HTML content"));
        assertTrue(displayed.contains("</body>"));
        assertTrue(displayed.contains("</html>"));
    }
}
