package com.github.atomic.controller;

import com.github.atomic.controller.base.BaseController;
import com.github.atomic.model.entity.Permission;
import com.github.atomic.model.entity.User;
import com.github.atomic.service.PermissionService;
import com.github.atomic.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@Tag(name = "Users")
public class UserController extends BaseController<User, UserService> {


    @Autowired
    PermissionService permissionService;

    public UserController(UserService service) {
        super(service);
    }


    @GetMapping("{id}/permission")
    public List<Permission> findAllByUserId(@PathVariable long id) {
        return permissionService.findAllByUserId(id);
    }
}
