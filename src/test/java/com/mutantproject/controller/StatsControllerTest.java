package com.mutantproject.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import com.mutantproject.dto.StatsDto;
import com.mutantproject.service.StatsServiceIF;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StatsControllerTest {
    
    @InjectMocks
    private StatsController statsControllerMock;

    @Mock
    private StatsServiceIF statsServiceIFMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStats_shouldReturnStatsDto() throws ArithmeticException, Exception {
        int dnaCountHuman = 100;
        int dnaCountMutant = 40;
        double ratio = 0.4;
        StatsDto statsDto = new StatsDto(dnaCountHuman, dnaCountMutant, ratio);
        when(statsServiceIFMock.getStats()).thenReturn(statsDto);
        StatsDto resp = statsControllerMock.getStats();
        assertSame(statsDto, resp);
    }
}