package com.emovies.movieMS.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emovies.movieMS.entity.Cinema;
import com.emovies.movieMS.entity.Movie;
import com.emovies.movieMS.service.CinemaServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/moviesapp")
@RequiredArgsConstructor
public class CinemaController {
	private final CinemaServiceImpl cinemaService;

	@PostMapping("/cinema")
	public ResponseEntity<Cinema> createCinema(@Valid @RequestBody Cinema cinemaData) {
		Cinema cinema = cinemaService.createCinemas(cinemaData);
		return ResponseEntity.status(HttpStatus.CREATED).body(cinema);
	}

	@GetMapping("/cinemas/{cinemaId}")
	public ResponseEntity<Cinema> findById(@PathVariable String cinemaId) {
		Cinema bycinemaId = cinemaService.findBycinemaId(cinemaId);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bycinemaId);

	}
	
	@GetMapping
    public ResponseEntity<List<Cinema>> getAllCinemas() {
		List<Cinema> cinemaList = cinemaService.getAllCinemas();
		return ResponseEntity.status(HttpStatus.OK).body(cinemaList);
    }
	
	@PostMapping("/{cinemaID}/movies")
    public ResponseEntity<Cinema> addMoviesToCinema(
            @PathVariable String cinemaID,
            @RequestBody List<String> movieIDs) {
        Cinema updatedCinema = cinemaService.addMoviesToCinema(cinemaID, movieIDs);
        return new ResponseEntity<>(updatedCinema, HttpStatus.OK);
    }

	@GetMapping("/cinemas/{cinemaID}/movies")
    public ResponseEntity<List<Movie>> getMoviesByCinemaId(@PathVariable String cinemaID) {
        List<Movie> movies = cinemaService.getMoviesByCinemaId(cinemaID);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
