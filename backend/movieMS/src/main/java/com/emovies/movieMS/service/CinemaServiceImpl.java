package com.emovies.movieMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.emovies.movieMS.entity.Cinema;
import com.emovies.movieMS.entity.Movie;
import com.emovies.movieMS.repository.CinemaRepository;
import com.emovies.movieMS.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CinemaServiceImpl implements CinemaService {

	private final CinemaRepository cinemaRepository;
	private final MovieRepository movieRepository;

	/**
	 * @return
	 */
	@Override
	public List<Cinema> getAllCinemas() {

		return cinemaRepository.findAll();
	}

	

	/**
	 * @param cinema
	 * @param cinemaId
	 * @return
	 */
	@Override
	public Cinema updateCinemas(Cinema cinema, String cinemaId) {
		return null;
	}

	/**
	 * @param cinemaId
	 * @return
	 */
	@Override
	public Cinema deleteCinemas(String cinemaId) {
		return null;
	}

	/**
	 * @param cinema
	 * @return
	 */
	@Override
	public Cinema createCinemas(Cinema cinema) {
		return cinemaRepository.save(cinema);
	}

	@Override
	public Set<Cinema> listCinemaByMovieId(String cinemaId) {

		return null;
	}

	@Override
	public Cinema findBycinemaId(String cinemaId) {
		Cinema cinema = cinemaRepository.findById(cinemaId).get();
		return cinema;
	}
	
	public Cinema addMoviesToCinema(String cinemaID, List<String> movieIDs) {
        Optional<Cinema> cinemaOpt = cinemaRepository.findById(cinemaID);
        if (!cinemaOpt.isPresent()) {
            throw new RuntimeException("Cinema not found");
        }

        Cinema cinema = cinemaOpt.get();
        List<Movie> existingMovies = cinema.getMovies();
        if (existingMovies == null) {
            existingMovies = new ArrayList<>();
        }

        // Fetch movies from the database
        List<Movie> newMovies = movieRepository.findAllById(movieIDs);

        // Add new movies to existing list
        existingMovies.addAll(newMovies);

        // Update the cinema with the new list of movies
        cinema.setMovies(existingMovies);

        return cinemaRepository.save(cinema);
    }
	
	public List<Movie> getMoviesByCinemaId(String cinemaID) {
        Optional<Cinema> cinemaOpt = cinemaRepository.findById(cinemaID);
        if (!cinemaOpt.isPresent()) {
            throw new RuntimeException("Cinema not found");
        }

        return cinemaOpt.get().getMovies();
    }
}
