package com.mutantproject.service;

import java.util.List;

import com.mutantproject.model.Dna;
import com.mutantproject.validator.MutantValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantServiceIF {

    private static final Logger logger = LogManager.getLogger(MutantServiceImpl.class);

    @Autowired
    private MutantValidator mutantValidator;

    @Autowired
    private DnaPersistenceServiceIF dnaPersistenceServiceIF;

    @Override
    public boolean isMutant(List<String> dna) {
        boolean isMutant = mutantValidator.isMutant(dna);
        logger.info("Result from current input: %" + isMutant + ", checking if this is already in db.");
        Dna dnaEntity = new Dna(dna, isMutant);
        dnaPersistenceServiceIF.persistDna(dnaEntity);
        return isMutant;
    }


  
}