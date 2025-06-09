package org.restapi.movie.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieAlreadyExistsException extends RuntimeException {

		String message="Data Already Exists";
}
