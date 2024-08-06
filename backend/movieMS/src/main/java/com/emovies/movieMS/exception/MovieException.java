package com.emovies.movieMS.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
    private String statusCode;


}
