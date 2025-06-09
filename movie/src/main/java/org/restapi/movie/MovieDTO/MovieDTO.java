package org.restapi.movie.MovieDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieDTO {
    private String Name;
    private String description;
    private String actor;
    private String actress;
    private String imageLink;
    private String trailerLink;
}
