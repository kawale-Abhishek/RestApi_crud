package org.restapi.movie.MovieEntity;

import org.restapi.movie.MovieDTO.MovieDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(generator = "movie_id")
    @SequenceGenerator(name = "movie_id", sequenceName = "movie_seq", initialValue = 1001, allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private String actor;
    private String actress;
    private String imageLink;
    private String trailerLink;

    public Movie(MovieDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.actor = dto.getActor();
        this.actress = dto.getActress();
        this.imageLink = dto.getImageLink();
        this.trailerLink = dto.getTrailerLink();
    }
}
