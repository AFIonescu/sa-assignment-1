package com.sa.carmanagement.controller;

import com.sa.carmanagement.service.UIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ui")
@RequiredArgsConstructor
public class UIController {

    private final UIService uiService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createUI(@RequestParam(defaultValue = "MODERN") String theme) {
        Map<String, String> ui = uiService.createUI(theme);
        return ResponseEntity.ok(ui);
    }
}
