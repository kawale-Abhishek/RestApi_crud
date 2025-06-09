package org.restapi.movie.MovieController;

import org.restapi.movie.MovieDTO.MovieDTO;
import org.restapi.movie.MovieEntity.Movie;
import org.restapi.movie.MovieService.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieService service;

    // Save a single movie
    @PostMapping("/movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveMovie(dto));
    }

  
    // Fetch all movies with pagination and sorting
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> fetchMovies(
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "false") boolean desc,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "30") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(service.fetchAll(sort, desc, page, size));
    }

    // Delete a movie by ID
    @DeleteMapping("/movie/{id}")
    @Operation(summary = "Delete movie by ID")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteMovieById(id));
    }

    // Update a movie record
    @PutMapping("/movie")
    @Operation(summary = "Update movie record")
    public ResponseEntity<Movie> updateMovie(@RequestBody MovieDTO movie) {
        return ResponseEntity.status(HttpStatus.OK).body(service.saveMovie(movie));
    }

    // Get movie by ID
    @GetMapping("/movie/{id}")
    @Operation(summary = "Get movie by ID")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getMovieById(id));
    }
}
