package com.mutantproject.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mutantproject.dto.StatsDto;
import com.mutantproject.model.Dna;
import com.mutantproject.repository.DnaRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsServiceIF {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public StatsDto getStats() throws Exception {
        List<Dna> listDna = dnaRepository.getAllDna();
        if(listDna.isEmpty()) {
            throw new Exception("Dna Stats cannot be calculated. There's no data inserted yet.");
        }
        int countDnaHuman = dnaRepository.getAllDna().stream().filter(dna -> !dna.isMutant()).collect(Collectors.toList()).size();
        int countDnaMutant = dnaRepository.getAllDna().stream().filter(dna -> dna.isMutant()).collect(Collectors.toList()).size();
        if(countDnaHuman == 0) {
            throw new ArithmeticException("countDnaHuman is zero. Cannot divide by zero to get the ratio.");
        }
        double ratio = ((double) countDnaMutant/countDnaHuman);
        StatsDto statsDto = new StatsDto(countDnaHuman, countDnaMutant, ratio);
        return statsDto;
    }
    
}