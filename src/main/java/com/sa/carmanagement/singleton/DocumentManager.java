package com.sa.carmanagement.singleton;

import com.sa.carmanagement.factory.Document;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DocumentManager {
    private static volatile DocumentManager instance;
    private final List<Document> documents;

    private DocumentManager() {
        this.documents = new ArrayList<>();
        log.info("DocumentManager: Instance created");
    }

    public static DocumentManager getInstance() {
        if (instance == null) {
            synchronized (DocumentManager.class) {
                if (instance == null) {
                    instance = new DocumentManager();
                }
            }
        }
        return instance;
    }

    public void addDocument(Document document) {
        documents.add(document);
        log.info("DocumentManager: Document added - Format: {}", document.getFormat());
    }

    public List<Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }

    public int getDocumentCount() {
        return documents.size();
    }

    public void clearDocuments() {
        documents.clear();
        log.info("DocumentManager: All documents cleared");
    }
}
