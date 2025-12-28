package com.sa.carmanagement.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentFactory {

    public Document createDocument(String type) {
        log.info("DocumentFactory: Creating document of type {}", type);

        return switch (type.toUpperCase()) {
            case "PDF" -> new PdfDocument();
            case "WORD", "DOCX" -> new WordDocument();
            case "HTML" -> new HtmlDocument();
            default -> throw new IllegalArgumentException("Unknown document type: " + type);
        };
    }
}
