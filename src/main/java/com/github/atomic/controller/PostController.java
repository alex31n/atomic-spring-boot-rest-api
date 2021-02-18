package com.github.atomic.controller;

import com.github.atomic.controller.base.BaseController;
import com.github.atomic.model.entity.Post;
import com.github.atomic.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
@Tag(name = "Posts")
public class PostController extends BaseController<Post, PostService> {


    public PostController(PostService service) {
        super(service);
    }

}
