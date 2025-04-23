package com.bottomline.exercise.autocomplete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import com.bottomline.exercise.autocomplete.service.AutoCompleteService;

@RestController
@RequestMapping("/api/autocomplete")
public class AutoCompleteController {

    @Autowired
    private AutoCompleteService autoCompleteService;
    
    @GetMapping
    public ResponseEntity<List<String>> getSuggestions(@RequestParam @NotBlank @Pattern(regexp = "^[A-Za-z]+$") String prefix) {
        List<String> results = autoCompleteService.autocomplete(prefix);
        return ResponseEntity.ok(results);
    }
}