package com.mutantproject.controller;

import com.mutantproject.dto.StatsDto;
import com.mutantproject.service.StatsServiceIF;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutantapp/v1")
public class StatsController {

    private static final Logger logger = LogManager.getLogger(StatsController.class);
    
    @Autowired
    private StatsServiceIF statsServiceIF;
    
    @GetMapping(value="/stats")
    public StatsDto getStats() throws ArithmeticException, Exception {
        logger.info("Checking stats.");
        return statsServiceIF.getStats();
    }
}