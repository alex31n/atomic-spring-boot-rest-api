package com.github.atomic.service;

import com.github.atomic.model.entity.User;
import com.github.atomic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@Transactional
public class UserService extends BaseService<User, UserRepository> {


    public UserService(UserRepository repository) {
        super(repository, "User");
    }

    public User findByIdentity(String input){
        return repository.findByUsernameOrEmailOrPhone(input,input,input)
                .orElseThrow(
                        ()-> new NotFoundException(entityName+" not found.")
                );
    }


}
