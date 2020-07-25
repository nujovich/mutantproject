package com.mutantproject.service;

import java.util.List;


import com.mutantproject.validator.MutantValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantServiceIF {

    @Autowired
    private MutantValidator mutantValidator;

    @Override
    public boolean isMutant(List<String> dna) {
        return mutantValidator.isMutant(dna);
    }


  
}