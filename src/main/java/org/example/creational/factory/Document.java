package org.example.creational.factory;

public interface Document {
    void setContent(String content);
    String getContent();
    void open();
    void save();
    void display();
}
