package com.inventorsoft.domain.service.base;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
