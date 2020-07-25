package com.mutantproject.controller;

import com.mutantproject.dto.DnaDto;
import com.mutantproject.service.MutantServiceIF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutantapp/v1")
public class MutantController {
    
    @Autowired
    private MutantServiceIF mutantService;

    @PostMapping(value = "/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaDto dnaDto) throws Exception {
        boolean result = mutantService.isMutant(dnaDto.getDna());
        if(result) {
            return ResponseEntity.ok("Is Mutant");
        } else {
            throw new Exception("Not a mutant");
        }
    }
}