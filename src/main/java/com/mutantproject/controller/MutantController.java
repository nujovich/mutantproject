package com.mutantproject.controller;

import com.mutantproject.dto.DnaDto;
import com.mutantproject.service.MutantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {
    
    @Autowired
    private MutantService mutantService;

    @PostMapping(value = "/mutant", consumes = "application/json")
    public ResponseEntity<String> isMutant(@RequestBody DnaDto dnaDto) throws IllegalArgumentException {
        try {
            boolean result = mutantService.isMutant(dnaDto.getDna());
            if(result) {
                return ResponseEntity.ok("Is Mutant");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}