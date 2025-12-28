package com.sa.carmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentRequest {
    @NotBlank(message = "Document type is required")
    private String type;

    @NotBlank(message = "Content is required")
    private String content;
}
