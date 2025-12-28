package com.sa.carmanagement.abstractfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModernButton implements Button {

    @Override
    public void paint() {
        log.info("ModernButton: Rendering modern styled button");
    }

    @Override
    public String getStyle() {
        return "Modern";
    }
}
