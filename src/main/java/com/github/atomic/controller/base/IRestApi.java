package com.github.atomic.controller.base;

import com.github.atomic.config.OpenAPIConfiguration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IRestApi<T> {
    @Operation(summary = "Get all items",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "search", description = "id==1 OR name==value"),
//                    @Parameter(in = ParameterIn.QUERY, name = "page", description = "0"),
//                    @Parameter(in = ParameterIn.QUERY, name = "size", description = "20"),
//                    @Parameter(in = ParameterIn.QUERY, name = "sort", description = "id,asc"),
//                    @Parameter(in = ParameterIn.QUERY, name = "pageable", hidden = true)
            }
    )
    @PageableAsQueryParam

    @GetMapping(produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<?> findAll(@RequestParam(name = "search", required = false, defaultValue = "") String search, @Parameter(hidden = true) Pageable pageable);



    @Operation(summary = "Get item by id")
    @GetMapping(value = "/{id:[0-9]+}", produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<?> findById(@PathVariable long id);


    @Operation(summary = "Insert item",
            security = @SecurityRequirement(name = OpenAPIConfiguration.securitySchemeName))
    @PostMapping(produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<?> create(@Valid  @RequestBody T object);


    @Operation(summary = "Update item",
            security = @SecurityRequirement(name = OpenAPIConfiguration.securitySchemeName))
    @PutMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody T object);


    @Operation(summary = "Delete item",
            security = @SecurityRequirement(name = OpenAPIConfiguration.securitySchemeName))
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    void delete(@PathVariable long id);

}
