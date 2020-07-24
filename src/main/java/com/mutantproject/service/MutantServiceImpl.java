package com.mutantproject.service;

import java.util.List;

import javax.inject.Inject;

import com.mutantproject.validator.MutantValidator;

import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantServiceIF {

    @Inject
    private MutantValidator mutantValidator;

    @Override
    public boolean isMutant(List<String> dna) {
        boolean isMutant = mutantValidator.isMutant(dna);
        return isMutant;
    }


  
}