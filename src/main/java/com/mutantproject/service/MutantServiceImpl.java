package com.mutantproject.service;

import java.util.List;

import com.mutantproject.model.Dna;
import com.mutantproject.validator.MutantValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantServiceIF {

    @Autowired
    private MutantValidator mutantValidator;

    @Autowired
    private DnaPersistenceServiceIF dnaPersistenceServiceIF;

    @Override
    public boolean isMutant(List<String> dna) {
        boolean isMutant = mutantValidator.isMutant(dna);
        Dna dnaEntity = new Dna(dna, isMutant);
        dnaPersistenceServiceIF.persistDna(dnaEntity);
        return isMutant;
    }


  
}