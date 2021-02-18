package com.github.atomic.controller.base;


import com.github.atomic.model.entity.IdEntity;
import com.github.atomic.service.BaseService;
import com.github.atomic.utils.ResponseUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public class BaseFindController<T extends IdEntity, S extends BaseService<T,?>> implements FindRestApi<T> {

   public S service;

    public BaseFindController(S service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<?> findAll(String search, Pageable pageable) {
        if (StringUtils.isEmpty(search)){
            Page<T> page = service.findAll(null, pageable);
            return ResponseUtils.responseEntity(page);
        }

        /*Node rootNode = new RSQLParser().parse(search);
        Specification<T> specs = rootNode.accept(new CustomRsqlVisitor<>());
        Page<T> page=  service.findAll(specs,pageable);

        return ResponseUtils.responseEntity(page);*/
        return ResponseUtils.responseEntity(service.findAll(RSQLJPASupport.toSpecification(search),pageable));
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        return ResponseUtils.responseEntity(service.findById(id));
    }

}
