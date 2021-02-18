package com.github.atomic.repository;

import com.github.atomic.model.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsernameOrEmailOrPhone(String username,String email,String phone);

    Optional<User> findByUsername(String username);
}
