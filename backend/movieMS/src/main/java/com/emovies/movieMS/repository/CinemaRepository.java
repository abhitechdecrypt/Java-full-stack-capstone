package com.emovies.movieMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emovies.movieMS.entity.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String>{

}
