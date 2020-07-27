package com.mutantproject.controller;

import com.mutantproject.dto.DnaDto;
import com.mutantproject.service.MutantServiceIF;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutantapp/v1")
public class MutantController {

    private static final Logger logger = LogManager.getLogger(MutantController.class);
    
    @Autowired
    private MutantServiceIF mutantService;

    @PostMapping(value = "/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaDto dnaDto) throws Exception {
        logger.info("Checking dna input.");
        boolean result = mutantService.isMutant(dnaDto.getDna());
        if(result) {
            logger.info("The resulting dna input is from a mutant.");
            return ResponseEntity.ok("Is Mutant");
        } else {
            logger.info("The resulting dna input is from a human.");
            throw new Exception("Not a mutant");
        }
    }
}