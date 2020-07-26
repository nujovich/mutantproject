package com.mutantproject.service;

import java.util.stream.Collectors;

import com.mutantproject.dto.StatsDto;
import com.mutantproject.repository.DnaRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsServiceIF {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public StatsDto getStats() {
        int countDnaHuman = dnaRepository.getAllDna().stream().filter(dna -> !dna.isMutant()).collect(Collectors.toList()).size();
        int countDnaMutant = dnaRepository.getAllDna().stream().filter(dna -> dna.isMutant()).collect(Collectors.toList()).size();
        if(countDnaMutant == 0) {
            throw new ArithmeticException("countDnaMutant is zero. Cannot divide by zero to get the ratio.");
        }
        double ratio = countDnaHuman/countDnaMutant;
        StatsDto statsDto = new StatsDto(countDnaHuman, countDnaMutant, ratio);
        return statsDto;
    }
    
}