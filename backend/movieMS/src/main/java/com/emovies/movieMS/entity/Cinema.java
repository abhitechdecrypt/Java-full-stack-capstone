package com.emovies.movieMS.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cinemas")
public class Cinema {

	@Id
	@NotBlank(message = "CinemaId can't be blank")
	private String cinemaID;

	@NotBlank(message = "cinema name can't be blank")
	private String name;

	@NotBlank(message = "Cinema Location can't be blank")
	private String address;

	@NotNull(message = "Cinema total screen can't be blank")
	private int totalScreens;

	@NotNull(message = "Cinema total seats can't be blank")
	private int totalSeats;

	@NotBlank(message = "Cinema facilities can't be blank")
	private String facilities;
	
	private boolean blocked;
		
	@ManyToMany
    @JoinTable(
        name = "cinema_movie",
        joinColumns = @JoinColumn(name = "cinemaID"),
        inverseJoinColumns = @JoinColumn(name = "movieID")
    )
	@JsonManagedReference
	private List<Movie> movies;
}
