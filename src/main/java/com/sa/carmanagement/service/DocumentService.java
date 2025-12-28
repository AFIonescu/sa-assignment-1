package com.sa.carmanagement.service;

import com.sa.carmanagement.dto.CreateDocumentRequest;
import com.sa.carmanagement.dto.DocumentResponse;
import com.sa.carmanagement.factory.Document;
import com.sa.carmanagement.factory.DocumentFactory;
import com.sa.carmanagement.singleton.DocumentManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentFactory documentFactory;

    public DocumentResponse createDocument(CreateDocumentRequest request) {
        log.info("DocumentService: Creating document - Type: {}", request.getType());

        // Use Factory Method pattern to create document
        Document document = documentFactory.createDocument(request.getType());
        document.save(request.getContent());

        // Use Singleton pattern to store document
        DocumentManager.getInstance().addDocument(document);

        return new DocumentResponse(
                document.getFormat(),
                request.getContent(),
                document.display()
        );
    }

    public List<DocumentResponse> getAllDocuments() {
        log.info("DocumentService: Retrieving all documents");

        return DocumentManager.getInstance().getAllDocuments().stream()
                .map(doc -> new DocumentResponse(
                        doc.getFormat(),
                        null,
                        doc.display()
                ))
                .collect(Collectors.toList());
    }

    public int getDocumentCount() {
        return DocumentManager.getInstance().getDocumentCount();
    }

    public void clearAllDocuments() {
        log.info("DocumentService: Clearing all documents");
        DocumentManager.getInstance().clearDocuments();
    }
}
