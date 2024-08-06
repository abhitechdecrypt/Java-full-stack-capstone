package com.emovies.movieMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emovies.movieMS.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>{
	List<Movie> findMoviesByCinemas_CinemaIDIn(List<String> cinemaIDs);

}
