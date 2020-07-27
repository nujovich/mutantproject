package com.mutantproject.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mutantproject.dto.StatsDto;
import com.mutantproject.model.Dna;
import com.mutantproject.repository.DnaRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsServiceIF {

    private static final Logger logger = LogManager.getLogger(StatsServiceImpl.class);
    
    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public StatsDto getStats() throws Exception {
        List<Dna> listDna = dnaRepository.getAllDna();
        if(listDna.isEmpty()) {
            logger.error("listDna is empty.");
            throw new Exception("Dna Stats cannot be calculated. There's no data inserted yet.");
        }
        int countDnaHuman = dnaRepository.getAllDna().stream().filter(dna -> !dna.isMutant()).collect(Collectors.toList()).size();
        int countDnaMutant = dnaRepository.getAllDna().stream().filter(dna -> dna.isMutant()).collect(Collectors.toList()).size();
        if(countDnaHuman == 0) {
            logger.error("countDnaHuman is zero.");
            throw new ArithmeticException("countDnaHuman is zero. Cannot divide by zero to get the ratio.");
        }
        double ratio = ((double) countDnaMutant/countDnaHuman);
        StatsDto statsDto = new StatsDto(countDnaHuman, countDnaMutant, ratio);
        return statsDto;
    }
    
}