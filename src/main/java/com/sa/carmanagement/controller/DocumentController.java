package com.sa.carmanagement.controller;

import com.sa.carmanagement.dto.CreateDocumentRequest;
import com.sa.carmanagement.dto.DocumentResponse;
import com.sa.carmanagement.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentResponse> createDocument(@Valid @RequestBody CreateDocumentRequest request) {
        DocumentResponse response = documentService.createDocument(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        List<DocumentResponse> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getDocumentCount() {
        return ResponseEntity.ok(documentService.getDocumentCount());
    }

    @DeleteMapping
    public ResponseEntity<Void> clearAllDocuments() {
        documentService.clearAllDocuments();
        return ResponseEntity.noContent().build();
    }
}
