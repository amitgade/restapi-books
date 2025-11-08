package dev.amitgade.javaweb.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    // handle BookNotFoundException
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException bookNotFoundException){
        BookErrorResponse errorResponse = new BookErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                bookNotFoundException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    // handle All exceptions except BookNotFoundException
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(Exception exception){
        BookErrorResponse errorResponse = new BookErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid request",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
