package com.github.atomic.service;

import com.github.atomic.model.entity.IdEntity;
import com.github.atomic.repository.BaseRepository;
import com.github.atomic.utils.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public class BaseService<M extends IdEntity, R extends BaseRepository<M>>{

    R repository;
    String entityName="";

    public BaseService(R repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;

    }

    public Page<M> findAll(Specification<M> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public M findById(long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> ExceptionUtils.notFoundException(entityName+" not found")
                );
    }

    public M create(M object) {
        object.setId(null);
        return repository.save(object);
    }

    public M update(long id, M object) {

        if (!repository.existsById(id)) {
            throw ExceptionUtils.notFoundException(entityName+" not found");
        }
        object.setId(id);
        return repository.save(object);
    }


    public void deleteById(long id) {

        if (!repository.existsById(id)) {
            throw ExceptionUtils.notFoundException(
                    entityName+" not found"
            );
        }

        repository.deleteById(id);

    }
}
