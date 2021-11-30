package com.example.tournament.service;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class GeneralService<ENTITY, ID extends Integer> {
    CrudRepository<ENTITY, ID> repository;

    public GeneralService(CrudRepository<ENTITY, ID> repository) {
        this.repository = repository;
    }

    public Optional<ENTITY> findById(ID id) {
        return repository.findById(id);
    }

    public ENTITY getById(ID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
