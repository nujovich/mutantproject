package com.mutantproject.dto;

import java.util.List;

public class DnaDto {

    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "DnaDto [dna=" + dna + "]";
    }
    
}