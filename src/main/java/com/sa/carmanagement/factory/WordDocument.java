package com.sa.carmanagement.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WordDocument implements Document {
    private String content;

    @Override
    public void save(String content) {
        this.content = content;
        log.info("WordDocument: Saving content to DOCX format");
    }

    @Override
    public String display() {
        return "Word Document: " + content;
    }

    @Override
    public String getFormat() {
        return "DOCX";
    }
}
