package org.example.creational.factory;

public class HtmlDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening HTML document");
    }
}
