package org.example.creational.factory;

public class WordDocument implements Document {
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
        System.out.println("Opening Word document");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document to .docx file");
    }

    @Override
    public void display() {
        System.out.println("[Word Document]");
        System.out.println(content);
        System.out.println("[End of Document]");
    }
}
