package com.sa.carmanagement.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlDocument implements Document {
    private String content;

    @Override
    public void save(String content) {
        this.content = content;
        log.info("HtmlDocument: Saving content to HTML format");
    }

    @Override
    public String display() {
        return "<html><body>" + content + "</body></html>";
    }

    @Override
    public String getFormat() {
        return "HTML";
    }
}
