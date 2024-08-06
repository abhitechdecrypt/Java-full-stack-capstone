package com.emovies.movieMS.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "movies")
public class Movie {

	@Id
	private String movieID;

	@NotBlank(message = "Movies title can't be blank")
	private String title;

	@NotBlank(message = "Movies director can't be blank")
	private String director;

	@NotBlank(message = "Movies genre can't be blank")
	private String genre;

	@NotNull(message = "Movies releaseDate can't be blank")
	private LocalDateTime releaseDate;

	@NotNull(message = "Movies duration can't be blank")
	private double duration;

	@NotNull(message = "Movies rating can't be blank")
	private double rating;

	@NotBlank(message = "Movies description can't be blank")
	private String description;

	@NotBlank(message = "Movies language can't be blank")
	private String language;

	@NotBlank(message = "Movies posterUrl can't be blank")
	private String posterUrl;
	
	@ManyToMany(mappedBy = "movies")
	@JsonBackReference
	private List<Cinema> cinemas;

	
}
