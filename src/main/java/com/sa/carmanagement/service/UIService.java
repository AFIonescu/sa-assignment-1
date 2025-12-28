package com.sa.carmanagement.service;

import com.sa.carmanagement.abstractfactory.Button;
import com.sa.carmanagement.abstractfactory.TextField;
import com.sa.carmanagement.abstractfactory.UIFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UIService {

    private final UIFactory modernUIFactory;
    private final UIFactory classicUIFactory;

    public UIService(@Qualifier("modernUIFactory") UIFactory modernUIFactory,
                     @Qualifier("classicUIFactory") UIFactory classicUIFactory) {
        this.modernUIFactory = modernUIFactory;
        this.classicUIFactory = classicUIFactory;
    }

    public Map<String, String> createUI(String theme) {
        log.info("UIService: Creating UI with theme: {}", theme);

        UIFactory factory = getFactory(theme);
        Button button = factory.createButton();
        TextField textField = factory.createTextField();

        button.paint();
        textField.render();

        Map<String, String> result = new HashMap<>();
        result.put("theme", factory.getTheme());
        result.put("button", button.getStyle() + " Button");
        result.put("textField", textField.getStyle() + " TextField");

        return result;
    }

    private UIFactory getFactory(String theme) {
        return switch (theme.toUpperCase()) {
            case "MODERN" -> modernUIFactory;
            case "CLASSIC" -> classicUIFactory;
            default -> throw new IllegalArgumentException("Unknown theme: " + theme);
        };
    }
}
