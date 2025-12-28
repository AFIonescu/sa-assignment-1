package com.sa.carmanagement.service;

import com.sa.carmanagement.dto.CreateDocumentRequest;
import com.sa.carmanagement.dto.DocumentResponse;
import com.sa.carmanagement.factory.DocumentFactory;
import com.sa.carmanagement.singleton.DocumentManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentServiceTest {

    private DocumentService documentService;
    private DocumentFactory documentFactory;

    @BeforeEach
    void setUp() {
        documentFactory = new DocumentFactory();
        documentService = new DocumentService(documentFactory);
        DocumentManager.getInstance().clearDocuments();
    }

    @AfterEach
    void tearDown() {
        DocumentManager.getInstance().clearDocuments();
    }

    @Test
    void testCreatePdfDocument() {
        CreateDocumentRequest request = new CreateDocumentRequest("PDF", "Test PDF content");
        DocumentResponse response = documentService.createDocument(request);

        assertNotNull(response);
        assertEquals("PDF", response.getFormat());
        assertEquals("Test PDF content", response.getContent());
        assertTrue(response.getDisplayContent().contains("PDF Document"));
    }

    @Test
    void testCreateWordDocument() {
        CreateDocumentRequest request = new CreateDocumentRequest("WORD", "Test Word content");
        DocumentResponse response = documentService.createDocument(request);

        assertNotNull(response);
        assertEquals("DOCX", response.getFormat());
        assertTrue(response.getDisplayContent().contains("Word Document"));
    }

    @Test
    void testCreateHtmlDocument() {
        CreateDocumentRequest request = new CreateDocumentRequest("HTML", "Test HTML content");
        DocumentResponse response = documentService.createDocument(request);

        assertNotNull(response);
        assertEquals("HTML", response.getFormat());
        assertTrue(response.getDisplayContent().contains("<html>"));
        assertTrue(response.getDisplayContent().contains("Test HTML content"));
    }

    @Test
    void testGetAllDocuments() {
        documentService.createDocument(new CreateDocumentRequest("PDF", "Content 1"));
        documentService.createDocument(new CreateDocumentRequest("WORD", "Content 2"));

        List<DocumentResponse> documents = documentService.getAllDocuments();

        assertEquals(2, documents.size());
    }

    @Test
    void testGetDocumentCount() {
        assertEquals(0, documentService.getDocumentCount());

        documentService.createDocument(new CreateDocumentRequest("PDF", "Test"));
        assertEquals(1, documentService.getDocumentCount());

        documentService.createDocument(new CreateDocumentRequest("HTML", "Test"));
        assertEquals(2, documentService.getDocumentCount());
    }

    @Test
    void testClearAllDocuments() {
        documentService.createDocument(new CreateDocumentRequest("PDF", "Test 1"));
        documentService.createDocument(new CreateDocumentRequest("WORD", "Test 2"));

        assertEquals(2, documentService.getDocumentCount());

        documentService.clearAllDocuments();

        assertEquals(0, documentService.getDocumentCount());
        assertTrue(documentService.getAllDocuments().isEmpty());
    }
}
