package com.domain.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseService<Entity, Id> {
    CrudRepository<Entity, Id> crudRepository;

    public Optional<Entity> findById(Id id) {
        return crudRepository.findById(id);
    }

    public Entity getById(Id id) {
        return crudRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Id id) {
        crudRepository.deleteById(id);
    }

    public List<Entity> getAll() {
        return (List<Entity>) crudRepository.findAll();
    }

    public Entity add(Entity entity) {
        return crudRepository.save(entity);
    }
}
