package com.mutantproject.service;

import java.util.concurrent.CompletableFuture;

import com.mutantproject.model.Dna;

public interface DnaPersistenceServiceIF {
    public CompletableFuture<Dna> persistDna(Dna dna);
}