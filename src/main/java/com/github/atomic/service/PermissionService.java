package com.github.atomic.service;

import com.github.atomic.model.entity.Permission;
import com.github.atomic.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionService extends BaseService<Permission, PermissionRepository> {


    public PermissionService(PermissionRepository repository) {
        super(repository, "Permission");
    }

    public List<Permission> findAllByUserId(long userId){
        return repository.findAllByUserId(userId);
    }

    public boolean isPermissionAvailable(long userId, String permissionTag){

        return repository.isPermissionAvailable(userId,permissionTag)>0;
    }
}
