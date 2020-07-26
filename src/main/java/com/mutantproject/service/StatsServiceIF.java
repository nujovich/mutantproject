package com.mutantproject.service;

import com.mutantproject.dto.StatsDto;

public interface StatsServiceIF {
    
    public StatsDto getStats() throws ArithmeticException;
}