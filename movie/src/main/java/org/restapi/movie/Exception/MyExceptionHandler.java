package org.restapi.movie.Exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<HashMap<String, String>> handleMovieAlreadyExists(MovieAlreadyExistsException ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<HashMap<String, String>> handleNoResourceFound(NoResourceFoundException ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "URL Not Mapped");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<HashMap<String, String>> handleMovieNotFound(MovieNotFoundException ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

}
