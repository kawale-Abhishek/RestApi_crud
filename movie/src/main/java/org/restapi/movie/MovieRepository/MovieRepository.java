package org.restapi.movie.MovieRepository;

import org.restapi.movie.MovieEntity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByName(String name);

    // Remove this method! The inherited existsById(Long id) is sufficient.
    // boolean existsById(String name);  <-- REMOVE THIS
}
