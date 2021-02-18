package com.github.atomic.model.login;

import com.github.atomic.model.entity.User;
import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private User user;
}
