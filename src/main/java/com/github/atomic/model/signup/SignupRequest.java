package com.github.atomic.model.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

//    @NotEmpty
//    @Size(min = 3, message = "Username must be greater than or equal to 3")
    private String username;

    @NotEmpty
    @Size(min = 8,max = 32)
    private String password;

    @Email
    private String email;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String firstName;

    private String lastName;

    private String type;

}
