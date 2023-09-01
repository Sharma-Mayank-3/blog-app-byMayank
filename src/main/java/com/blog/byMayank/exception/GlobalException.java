package com.blog.byMayank.exception;

import com.blog.byMayank.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse("exception occours", false, "user-service", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
