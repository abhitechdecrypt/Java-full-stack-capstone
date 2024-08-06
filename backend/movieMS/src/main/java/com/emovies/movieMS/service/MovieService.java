package com.emovies.movieMS.service;

import com.emovies.movieMS.entity.Movie;

import java.util.List;

public interface MovieService {
	List<Movie> getAllMovies();

    List<Movie> listCinemaByMovieId(String movieId);

    Movie updateMovie(Movie movie, String movieId);

    Movie deleteMovies(String movieId);

    Movie createMovies(Movie movie);

}
