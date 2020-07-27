package com.mutantproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mutantproject.dto.StatsDto;
import com.mutantproject.model.Dna;
import com.mutantproject.repository.DnaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StatsServiceImplTest {

    @InjectMocks
    private StatsServiceImpl statsServiceImplMock;

    @Mock
    private DnaRepository dnaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStats_shouldThrowArithmeticException() {
        List<Dna> listDna = new ArrayList<>();
        List<String> dnaMutant = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        listDna.add(new Dna(dnaMutant, true));
        when(dnaRepository.getAllDna()).thenReturn(listDna);
        ArithmeticException ex = assertThrows(ArithmeticException.class, 
            () -> {statsServiceImplMock.getStats();
        });
        assertEquals(1, listDna.size());
        assertEquals("countDnaHuman is zero. Cannot divide by zero to get the ratio.", ex.getMessage());
    }

    @Test
    public void getStats_shouldReturnStatsDto() throws Exception {
        List<Dna> listDna = new ArrayList<>();
        List<String> dnaHuman = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");
        List<String> dnaMutant = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        listDna.add(new Dna(dnaHuman, false));
        listDna.add(new Dna(dnaMutant, true));
        int countDnaHuman = 1;
        int countDnaMutant = 1;
        double ratio = 1.0;
        when(dnaRepository.getAllDna()).thenReturn(listDna);
        StatsDto statsDto = new StatsDto(countDnaHuman, countDnaMutant, ratio);
        StatsDto actual = statsServiceImplMock.getStats();
        assertEquals(statsDto.getCountDnaHuman(), actual.getCountDnaHuman());
        assertEquals(statsDto.getCountDnaMutant(), actual.getCountDnaMutant());
        assertEquals(statsDto.getRatio(), actual.getRatio());
        assertEquals(2, listDna.size());
    }

    @Test
    public void getStats_shouldThrowException() {
        List<Dna> listDna = new ArrayList<>();

        when(dnaRepository.getAllDna()).thenReturn(listDna);
        Exception ex = assertThrows(Exception.class, 
            () -> {statsServiceImplMock.getStats();
        });
        assertEquals(0, listDna.size());
        assertEquals("Dna Stats cannot be calculated. There's no data inserted yet.", ex.getMessage());
    }
    

}