package com.example.Mini.Project.Prodemi.Exception;


import com.example.Mini.Project.Prodemi.Validation.ResponseData;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseData<Object>> handleJsonParseException(HttpMessageNotReadableException ignoredEx) {
        ResponseData<Object> responseData = new ResponseData<>();
        responseData.setStatus("failed");
        responseData.setMessages(Collections.singletonList("Data yang dimasukkan tidak sesuai"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ResponseData<Object>>handlePropertyReferenceException (PropertyReferenceException ignoredEx){
        ResponseData <Object> responseData = new ResponseData<>();
        responseData.setStatus("failed");
        responseData.setMessages(Collections.singletonList("Terjadi Kesalahan dalam memuat data"));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseData<Object>>handleTypeMismatch (MethodArgumentTypeMismatchException ignoredEx){
        ResponseData <Object> responseData = new ResponseData<>();
        responseData.setStatus("failed");
        responseData.setMessages(Collections.singletonList("Format data tidak sesuai"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResponseData<Object>> handleCategoryNotFoundException(CategoryNotFoundException ignoredEx) {
        ResponseData <Object> responseData = new ResponseData<>();
        responseData.setStatus("failed");
        responseData.setMessages(Collections.singletonList("Category not found"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseData<Object>> handleProductNotFoundException(ProductNotFoundException ignoredEx) {
        ResponseData <Object> responseData = new ResponseData<>();
        responseData.setStatus("failed");
        responseData.setMessages(Collections.singletonList("Product not found"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseData<Object>>handleIllegalArgumentException (IllegalArgumentException ignoredEx){
        ResponseData <Object> responseData = new ResponseData<>();
        responseData.setStatus("failed");
        responseData.setMessages(Collections.singletonList("Total pay must be greater than or equal to total amount"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<ResponseData<Object>> handleResouceNotFoundException(ResouceNotFoundException ignoredEx) {
        ResponseData <Object> responseData = new ResponseData<>();
        responseData.setStatus("failed");
        responseData.setMessages(Collections.singletonList("Subtotal salah"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
    }
}
