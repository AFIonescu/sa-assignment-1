package com.sa.carmanagement.singleton;

import com.sa.carmanagement.factory.Document;
import com.sa.carmanagement.factory.HtmlDocument;
import com.sa.carmanagement.factory.PdfDocument;
import com.sa.carmanagement.factory.WordDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentManagerTest {

    @AfterEach
    void tearDown() {
        DocumentManager.getInstance().clearDocuments();
    }

    @Test
    void testGetInstance() {
        DocumentManager instance1 = DocumentManager.getInstance();
        DocumentManager instance2 = DocumentManager.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2);
    }

    @Test
    void testAddDocument() {
        DocumentManager manager = DocumentManager.getInstance();
        Document document = new PdfDocument();
        document.save("Test content");

        manager.addDocument(document);

        assertEquals(1, manager.getDocumentCount());
    }

    @Test
    void testAddMultipleDocuments() {
        DocumentManager manager = DocumentManager.getInstance();

        Document pdf = new PdfDocument();
        pdf.save("PDF content");

        Document word = new WordDocument();
        word.save("Word content");

        Document html = new HtmlDocument();
        html.save("HTML content");

        manager.addDocument(pdf);
        manager.addDocument(word);
        manager.addDocument(html);

        assertEquals(3, manager.getDocumentCount());
    }

    @Test
    void testGetAllDocuments() {
        DocumentManager manager = DocumentManager.getInstance();

        Document pdf = new PdfDocument();
        pdf.save("Test 1");
        manager.addDocument(pdf);

        Document word = new WordDocument();
        word.save("Test 2");
        manager.addDocument(word);

        assertEquals(2, manager.getAllDocuments().size());
    }

    @Test
    void testGetAllDocumentsReturnsNewList() {
        DocumentManager manager = DocumentManager.getInstance();

        Document document = new PdfDocument();
        document.save("Test");
        manager.addDocument(document);

        var list1 = manager.getAllDocuments();
        var list2 = manager.getAllDocuments();

        assertNotSame(list1, list2);
        assertEquals(list1.size(), list2.size());
    }

    @Test
    void testClearDocuments() {
        DocumentManager manager = DocumentManager.getInstance();

        Document pdf = new PdfDocument();
        pdf.save("Test");
        manager.addDocument(pdf);

        assertEquals(1, manager.getDocumentCount());

        manager.clearDocuments();

        assertEquals(0, manager.getDocumentCount());
        assertTrue(manager.getAllDocuments().isEmpty());
    }

    @Test
    void testGetDocumentCount() {
        DocumentManager manager = DocumentManager.getInstance();

        assertEquals(0, manager.getDocumentCount());

        manager.addDocument(new PdfDocument());
        assertEquals(1, manager.getDocumentCount());

        manager.addDocument(new WordDocument());
        assertEquals(2, manager.getDocumentCount());

        manager.addDocument(new HtmlDocument());
        assertEquals(3, manager.getDocumentCount());
    }

    @Test
    void testSingletonPersistenceAcrossReferences() {
        DocumentManager manager1 = DocumentManager.getInstance();
        Document doc = new PdfDocument();
        doc.save("Test");
        manager1.addDocument(doc);

        DocumentManager manager2 = DocumentManager.getInstance();

        assertEquals(1, manager2.getDocumentCount());
        assertSame(manager1, manager2);
    }
}
