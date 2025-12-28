package com.sa.carmanagement.abstractfactory;

public interface UIFactory {
    Button createButton();
    TextField createTextField();
    String getTheme();
}
