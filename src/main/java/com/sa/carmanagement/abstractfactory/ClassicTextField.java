package com.sa.carmanagement.abstractfactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassicTextField implements TextField {

    @Override
    public void render() {
        log.info("ClassicTextField: Rendering classic styled text field");
    }

    @Override
    public String getStyle() {
        return "Classic";
    }
}
