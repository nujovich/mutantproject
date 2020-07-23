package com.mutantproject.service;

import java.util.List;

import com.mutantproject.validator.MutantValidator;

import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantServiceIF {

    @Override
    public boolean isMutant(List<String> dna) {
        boolean isMutant = MutantValidator.isMutant(dna);
        return isMutant;
    }


  
}