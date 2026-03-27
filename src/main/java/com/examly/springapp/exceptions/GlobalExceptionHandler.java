

package com.examly.springapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler 
{
    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<String> unauthError(UnauthorizeException ex)
    {
        return ResponseEntity.status(401).body(ex.getMessage());
    } 
    
    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<String> duplicateProduct(DuplicateProductException ex)
    {
        return ResponseEntity.status(401).body(ex.getMessage());
    } 


    @ExceptionHandler(DuplicateOrderException.class)
    public ResponseEntity<String> duplicateOrder(DuplicateOrderException ex)
    {
        return ResponseEntity.status(401).body(ex.getMessage());
    } 

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFound(OrderNotFoundException ex)
    {
        return ResponseEntity.status(401).body(ex.getMessage());
    } 
}
