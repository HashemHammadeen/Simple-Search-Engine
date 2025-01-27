package com.zerowhisper.codesearchengine.controllers;

import com.zerowhisper.codesearchengine.models.MQueryRequest;
import com.zerowhisper.codesearchengine.services.JavaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JavaSearchController {

    private final JavaSearchService javaSearchService;

    @Autowired
    public JavaSearchController(JavaSearchService javaSearchService) {
        this.javaSearchService = javaSearchService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchJavaTokens(@RequestBody MQueryRequest query) {

        try {
            return ResponseEntity.ok().body(javaSearchService.search(query));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}
