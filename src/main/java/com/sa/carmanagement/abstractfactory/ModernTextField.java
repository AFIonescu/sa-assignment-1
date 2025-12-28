package com.sa.carmanagement.abstractfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModernTextField implements TextField {

    @Override
    public void render() {
        log.info("ModernTextField: Rendering modern styled text field");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}
