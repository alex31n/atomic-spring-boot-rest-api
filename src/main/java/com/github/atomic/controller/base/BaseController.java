package com.github.atomic.controller.base;

import com.github.atomic.config.EnvironmentConfig;
import com.github.atomic.model.entity.IdEntity;
import com.github.atomic.service.BaseService;
import com.github.atomic.utils.ResponseUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class BaseController<T extends IdEntity, S extends BaseService<T, ?>> implements IRestApi<T> {

    public S service;

    @Autowired
    EnvironmentConfig environmentConfig;

    public BaseController(S service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<?> findAll(String search, Pageable pageable) {
        if (StringUtils.isEmpty(search)) {
            Page<T> page = service.findAll(null, pageable);
            return ResponseUtils.responseEntity(page);
        }

        return ResponseUtils.responseEntity(
                service.findAll(
                        RSQLJPASupport.toSpecification(search), pageable
                )
        );
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        return ResponseUtils.responseEntity(service.findById(id));
    }

    @Override
    public ResponseEntity<?> create(T object) {
        return ResponseUtils.responseEntity(service.create(object));
    }

    @Override
    public ResponseEntity<?> update(long id, T object) {
        return ResponseUtils.responseEntity(service.update(id, object));
    }

    @Override
    public void delete(long id) {
        service.deleteById(id);
    }
}
