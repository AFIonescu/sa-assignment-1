package com.sa.carmanagement.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfDocument implements Document {
    private String content;

    @Override
    public void save(String content) {
        this.content = content;
        log.info("PdfDocument: Saving content to PDF format");
    }

    @Override
    public String display() {
        return "PDF Document: " + content;
    }

    @Override
    public String getFormat() {
        return "PDF";
    }
}
