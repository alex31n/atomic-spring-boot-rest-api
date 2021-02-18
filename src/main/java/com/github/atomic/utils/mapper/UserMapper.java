package com.github.atomic.utils.mapper;

import com.github.atomic.model.entity.User;
import com.github.atomic.model.signup.SignupRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signupRequestToUser(SignupRequest request);
}
