package com.sa.carmanagement.abstractfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassicButton implements Button {

    @Override
    public void paint() {
        log.info("ClassicButton: Rendering classic styled button");
    }

    @Override
    public String getStyle() {
        return "Classic";
    }
}
