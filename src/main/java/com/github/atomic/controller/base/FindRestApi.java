package com.github.atomic.controller.base;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface FindRestApi<T> {

    @Operation(summary = "Get all items",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "search", description = "id==1 OR name==value"),
            }
    )
    @PageableAsQueryParam

    @GetMapping(produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<?> findAll(@RequestParam(name = "search", required = false, defaultValue = "") String search, @Parameter(hidden = true)Pageable pageable);


    @Operation(summary = "Get item by id")
    @GetMapping(value = "/{id:[0-9]+}", produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<?> findById(@PathVariable long id);

}
