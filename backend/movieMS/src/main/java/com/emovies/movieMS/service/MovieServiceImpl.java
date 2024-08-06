package com.emovies.movieMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emovies.movieMS.entity.Cinema;
import com.emovies.movieMS.entity.Movie;
import com.emovies.movieMS.repository.CinemaRepository;
import com.emovies.movieMS.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList;
    }

    @Override
    public List<Movie> listCinemaByMovieId(String cinemaId) {
    	 Cinema cinema = cinemaRepository.findById(cinemaId).orElse(null);
    	    return cinema != null ? new ArrayList<>(cinema.getMovies()) : new ArrayList<>();
    }
    
    @Override
    public Movie updateMovie(Movie movie, String movieId) {
        return null;
    }

    @Override
    public Movie deleteMovies(String movieId) {
        return null;
    }
    
    public Movie createMovie(Movie movie, List<String> cinemaIDs) {
        // Fetch cinemas from the database
    	Random random = new Random();
    	String movieId = "MOVIE_"+ (1000000000L + (long) (random.nextDouble() * 9000000000L));
        movie.setMovieID(movieId);
        List<Cinema> cinemas = cinemaRepository.findAllById(cinemaIDs);
        movie.setCinemas(cinemas);
        return movieRepository.save(movie);
    }
    
    public List<String> getMoviesByCinemaIds(List<String> cinemaIDs) {
        List<Movie> movies = movieRepository.findMoviesByCinemas_CinemaIDIn(cinemaIDs);
        return movies.stream().map(Movie::getMovieID).distinct().collect(Collectors.toList());
    }

    @Override
    public Movie createMovies(Movie movie) {
        Random random = new Random();
        String movieId = "MOVIE_"+ (1000000000L + (long) (random.nextDouble() * 9000000000L));
        movie.setMovieID(movieId);
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie;
    }
    public Movie addCinemasToMovie(String movieID, List<String> cinemaIDs) {
        Optional<Movie> movieOpt = movieRepository.findById(movieID);
        if (!movieOpt.isPresent()) {
            throw new RuntimeException("Movie not found");
        }

        Movie movie = movieOpt.get();
        List<Cinema> existingCinemas = movie.getCinemas();
        if (existingCinemas == null) {
            existingCinemas = new ArrayList<>();
        }
        List<Cinema> newCinemas = cinemaRepository.findAllById(cinemaIDs);

        existingCinemas.addAll(newCinemas);

        movie.setCinemas(existingCinemas);

        return movieRepository.save(movie);
    }

    public List<Cinema> getCinemasByMovieId(String movieID) {
        Optional<Movie> movieOpt = movieRepository.findById(movieID);
        if (!movieOpt.isPresent()) {
            throw new RuntimeException("Movie not found");
        }

        return movieOpt.get().getCinemas();
    }
    
    
    

}
