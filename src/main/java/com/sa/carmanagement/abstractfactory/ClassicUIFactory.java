package com.sa.carmanagement.abstractfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("classicUIFactory")
public class ClassicUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        log.info("ClassicUIFactory: Creating classic button");
        return new ClassicButton();
    }

    @Override
    public TextField createTextField() {
        log.info("ClassicUIFactory: Creating classic text field");
        return new ClassicTextField();
    }

    @Override
    public String getTheme() {
        return "Classic";
    }
}
