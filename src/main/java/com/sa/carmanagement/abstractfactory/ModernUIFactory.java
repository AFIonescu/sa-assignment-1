package com.sa.carmanagement.abstractfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("modernUIFactory")
public class ModernUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        log.info("ModernUIFactory: Creating modern button");
        return new ModernButton();
    }

    @Override
    public TextField createTextField() {
        log.info("ModernUIFactory: Creating modern text field");
        return new ModernTextField();
    }

    @Override
    public String getTheme() {
        return "Modern";
    }
}
