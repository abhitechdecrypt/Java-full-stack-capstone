package com.emovies.movieMS.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emovies.movieMS.entity.Cinema;
import com.emovies.movieMS.entity.Movie;
import com.emovies.movieMS.service.MovieServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/moviesApp")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> allMovies = movieService.getAllMovies();
        return ResponseEntity.status(HttpStatus.OK).body(allMovies);
    }
    
    @PostMapping
    public ResponseEntity<Movie> createMovie(
            @RequestBody Movie movie,
            @RequestParam List<String> cinemaIDs) {
        Movie createdMovie = movieService.createMovie(movie, cinemaIDs);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }
    
    @GetMapping("/by-cinemas")
    public ResponseEntity<List<String>> getMoviesByCinemaIds(@RequestParam List<String> cinemaIDs) {
        List<String> movieIDs = movieService.getMoviesByCinemaIds(cinemaIDs);
        return new ResponseEntity<>(movieIDs, HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovies(@RequestBody Movie movie){
        Movie movies = movieService.createMovies(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movies);
    }
    
    @PostMapping("/{movieID}/cinemas")
    public ResponseEntity<Movie> addCinemasToMovie(
            @PathVariable String movieID,
            @RequestBody List<String> cinemaIDs) {
        Movie updatedMovie = movieService.addCinemasToMovie(movieID, cinemaIDs);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @GetMapping("/movies/{movieID}/cinemas")
    public ResponseEntity<List<Cinema>> getCinemasByMovieId(@PathVariable String movieID) {
        List<Cinema> cinemas = movieService.getCinemasByMovieId(movieID);
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

}
