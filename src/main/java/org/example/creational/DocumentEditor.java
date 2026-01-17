package org.example.creational;

import org.example.creational.factory.Document;
import org.example.creational.factory.DocumentFactory;

public class DocumentEditor {

    public Document createDocument(String type) {
        return DocumentFactory.createDocument(type);
    }

    public Document createDocument(String type, String content) {
        Document doc = DocumentFactory.createDocument(type);
        doc.setContent(content);
        return doc;
    }

    public static void main(String[] args) {
        DocumentEditor editor = new DocumentEditor();

        // Create and work with PDF document
        Document pdfDoc = editor.createDocument("PDF", "This is a PDF document content.");
        pdfDoc.open();
        pdfDoc.display();
        pdfDoc.save();

        System.out.println();

        // Create and work with Word document
        Document wordDoc = editor.createDocument("Word", "This is a Word document content.");
        wordDoc.open();
        wordDoc.display();
        wordDoc.save();

        System.out.println();

        // Create and work with HTML document
        Document htmlDoc = editor.createDocument("HTML", "This is an HTML document content.");
        htmlDoc.open();
        htmlDoc.display();
        htmlDoc.save();
    }
}
