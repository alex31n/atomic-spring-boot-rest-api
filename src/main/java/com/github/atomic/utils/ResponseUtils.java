package com.github.atomic.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;


public class ResponseUtils {

    public static ResponseEntity<?> responseEntity(Page<?> page){
        return ResponseEntity.ok(build(page));
    }

    public static <T> ResponseEntity<?> responseEntity(T obj){
        return ResponseEntity.ok(obj);
    }

    public static <T> FindAllResponse<T> build(Page<T> page){
        FindAllResponse<T> response = new FindAllResponse<>();
        response.setNumberOfElements(page.getNumberOfElements());
        response.setContent(page.getContent());
        response.setSize(page.getSize());
        response.setPage(page.getNumber());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        return response;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FindAllResponse<T> implements Serializable {
        List<T> content;
        int numberOfElements;
        int page;
        int size;
        long totalElements;
        int totalPages;
    }
}
