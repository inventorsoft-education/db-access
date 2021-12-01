package com.example.demo.service.base;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneralService<ENTITY, ID extends Integer> {
    CrudRepository<ENTITY, ID> repository;

    @Transactional(readOnly = true)
    public ENTITY getById(ID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<ENTITY> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Transactional
    public void save(ENTITY entity) {
        repository.save(entity);
    }

    @Transactional
    public void saveAll(List<ENTITY> list) {
        repository.saveAll(list);
    }

}
