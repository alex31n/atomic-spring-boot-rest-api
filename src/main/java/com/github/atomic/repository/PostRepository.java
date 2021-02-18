package com.github.atomic.repository;

import com.github.atomic.model.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PostRepository extends BaseRepository<Post> {

}
