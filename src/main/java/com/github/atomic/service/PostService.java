package com.github.atomic.service;

import com.github.atomic.model.entity.Post;
import com.github.atomic.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService extends BaseService<Post, PostRepository> {

    public PostService(PostRepository repository) {
        super(repository, "Post");
    }


}
