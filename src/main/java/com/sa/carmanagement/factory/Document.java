package com.sa.carmanagement.factory;

public interface Document {
    void save(String content);
    String display();
    String getFormat();
}
