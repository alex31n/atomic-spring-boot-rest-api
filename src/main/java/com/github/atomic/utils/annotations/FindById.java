package com.github.atomic.utils.annotations;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Operation(summary = "Get item by id")
@RequestMapping(method = RequestMethod.GET, value = "/{id:[0-9]+}", produces = "application/json")
@ResponseStatus(code = HttpStatus.OK)
public @interface FindById {
}
