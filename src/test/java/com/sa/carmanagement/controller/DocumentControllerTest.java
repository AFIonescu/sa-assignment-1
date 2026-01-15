package com.sa.carmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.carmanagement.dto.CreateDocumentRequest;
import com.sa.carmanagement.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DocumentController.class)
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateDocument() throws Exception {
        CreateDocumentRequest request = new CreateDocumentRequest("PDF", "Test content");

        when(documentService.createDocument(any())).thenReturn(
            new com.sa.carmanagement.dto.DocumentResponse("PDF", "Test content", "PDF: Test content")
        );

        mockMvc.perform(post("/api/documents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllDocuments() throws Exception {
        when(documentService.getAllDocuments()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/documents"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDocumentCount() throws Exception {
        when(documentService.getDocumentCount()).thenReturn(5);

        mockMvc.perform(get("/api/documents/count"))
                .andExpect(status().isOk());
    }

    @Test
    void testClearAllDocuments() throws Exception {
        mockMvc.perform(delete("/api/documents"))
                .andExpect(status().isNoContent());
    }
}
