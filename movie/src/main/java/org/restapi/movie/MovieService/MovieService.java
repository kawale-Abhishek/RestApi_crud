package org.restapi.movie.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import org.restapi.movie.Exception.MovieAlreadyExistsException;
import org.restapi.movie.Exception.MovieNotFoundException;
import org.restapi.movie.MovieDTO.MovieDTO;
import org.restapi.movie.MovieEntity.Movie;
import org.restapi.movie.MovieRepository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    // Save a movie from DTO
    public Movie saveMovie(MovieDTO dto) {
        if (repository.existsByName(dto.getName())) {
            throw new MovieAlreadyExistsException("Movie already exists: " + dto.getName());
        }
        Movie movie = new Movie(dto);
        return repository.save(movie);
    }

    // Fetch all movies with pagination and sorting
    public List<Movie> fetchAll(String sortField, boolean desc, int page, int size) {
        Sort sort = Sort.by(sortField);
        if (desc) sort = sort.descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Movie> result = repository.findAll(pageable);

        if (result.hasContent()) {
            return result.getContent();
        } else {
            throw new MovieNotFoundException("No movie records found.");
        }
    }

    // Fetch all movies (no pagination)
    public List<Movie> getAllMovies() {
        List<Movie> movies = repository.findAll();
        if (movies.isEmpty()) {
            throw new MovieNotFoundException("No movies found.");
        }
        return movies;
    }

    // Get movie by ID
    public Movie getMovieById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with ID " + id + " not found"));
    }

    // Delete movie by ID
    public String deleteMovieById(Long id) {
        if (!repository.existsById(id)) {
            throw new MovieNotFoundException("Movie with ID " + id + " not found");
        }
        repository.deleteById(id);
        return "Movie deleted successfully.";
    }

    // Update movie by ID using DTO
    public Movie updateMovie(Long id, MovieDTO dto) {
        Movie movie = repository.findById(id)
            .orElseThrow(() -> new MovieNotFoundException("Movie with ID " + id + " not found"));

        movie.setName(dto.getName());
        movie.setDescription(dto.getDescription());
        movie.setActor(dto.getActor());
        movie.setActress(dto.getActress());
        movie.setImageLink(dto.getImageLink());
        movie.setTrailerLink(dto.getTrailerLink());

        return repository.save(movie);
    }
}
