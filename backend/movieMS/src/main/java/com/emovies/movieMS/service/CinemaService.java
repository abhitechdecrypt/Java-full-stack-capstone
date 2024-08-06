package com.emovies.movieMS.service;

import java.util.List;
import java.util.Set;

import com.emovies.movieMS.entity.Cinema;

public interface CinemaService {

	List<Cinema> getAllCinemas();

    Set<Cinema> listCinemaByMovieId(String cinemaId);

    Cinema updateCinemas(Cinema cinema, String cinemaId);

    Cinema deleteCinemas(String cinemaId);

    Cinema createCinemas(Cinema cinema);
    
    Cinema findBycinemaId(String cinemaId);

}
