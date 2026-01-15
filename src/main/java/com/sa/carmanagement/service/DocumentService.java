package com.sa.carmanagement.service;

import com.sa.carmanagement.dto.CreateDocumentRequest;
import com.sa.carmanagement.dto.DocumentResponse;
import com.sa.carmanagement.factory.Document;
import com.sa.carmanagement.factory.DocumentFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentFactory documentFactory;
    private final List<Document> documents = new ArrayList<>();

    public DocumentResponse createDocument(CreateDocumentRequest request) {
        log.info("DocumentService: Creating document - Type: {}", request.getType());

        // Use Factory Method pattern to create document
        Document document = documentFactory.createDocument(request.getType());
        document.save(request.getContent());

        // Store document
        documents.add(document);

        return new DocumentResponse(
                document.getFormat(),
                request.getContent(),
                document.display()
        );
    }

    public List<DocumentResponse> getAllDocuments() {
        log.info("DocumentService: Retrieving all documents");

        return documents.stream()
                .map(doc -> new DocumentResponse(
                        doc.getFormat(),
                        null,
                        doc.display()
                ))
                .collect(Collectors.toList());
    }

    public int getDocumentCount() {
        return documents.size();
    }

    public void clearAllDocuments() {
        log.info("DocumentService: Clearing all documents");
        documents.clear();
    }
}
