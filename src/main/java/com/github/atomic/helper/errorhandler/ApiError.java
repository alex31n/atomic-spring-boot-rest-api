package com.github.atomic.helper.errorhandler;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {

    private Date timestamp;
    private int status;
    private String message;
    private String path;
    private List<String> errors;


    public ApiError(int status, String message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public ApiError(int status, String message, String path, List<String> errors) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }


    public ApiError(int status, String message, String path, String... error) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = Arrays.asList(error);
    }

    public ApiError(int status, WebRequest webRequest) {
        this.timestamp = new Date();
        this.status = status;
        this.path = ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    }

    public ApiError(int status, Map<String, Object> errorAttributes) {
        this.setStatus(status);
        this.setPath((String) errorAttributes.get("path"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTimestamp((Date) errorAttributes.get("timestamp"));
    }
}
