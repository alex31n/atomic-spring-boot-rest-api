package com.github.atomic.utils.annotations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.*;

@Operation(summary = "Get all items",
        parameters = {
                @Parameter(in = ParameterIn.QUERY, name = "search", description = "id==1 OR name==value")
        }
)
@PageableAsQueryParam

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.GET, produces = "application/json")
@ResponseStatus(code = HttpStatus.OK)
public @interface FindAll {
}
