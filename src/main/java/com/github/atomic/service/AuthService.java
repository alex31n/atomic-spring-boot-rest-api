package com.github.atomic.service;

import com.github.atomic.config.security.jwt.JwtUtils;
import com.github.atomic.model.entity.Status;
import com.github.atomic.model.entity.User;
import com.github.atomic.model.login.LoginRequest;
import com.github.atomic.model.login.LoginResponse;
import com.github.atomic.model.signup.SignupRequest;
import com.github.atomic.utils.mapper.UserMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@Slf4j
@Transactional
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    UserMapperImpl userMapper;

    public ResponseEntity<?> login(LoginRequest request) {

        LoginResponse loginResponse = null;

        try {

            User user = userService.findByIdentity(request.getUsername());
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                loginResponse = new LoginResponse() {{
                    setToken(jwtUtil.generateToken(user));
                    setUser(user);
                }};

            }


        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password invalid");
        }

        if (loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password invalid");
        }

    }


    public ResponseEntity<?> signup(SignupRequest request) {

        if (StringUtils.isEmpty(request.getUsername())){
            request.setUsername(UUID.randomUUID().toString());
        }

        User user= userMapper.signupRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // for default
        user.setStatus(new Status(){{
            setId(1L);
        }});
        user.setType(User.Type.USER);

        return ResponseEntity.ok(userService.create(user));

    }
}
