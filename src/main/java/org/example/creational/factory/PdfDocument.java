package org.example.creational.factory;

public class PdfDocument implements Document {
    private String content = "";

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void open() {
        System.out.println("Opening PDF document");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document to .pdf file");
    }

    @Override
    public void display() {
        System.out.println("=== PDF Document ===");
        System.out.println(content);
        System.out.println("====================");
    }
}
