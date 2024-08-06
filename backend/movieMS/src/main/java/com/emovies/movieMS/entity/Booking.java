package com.emovies.movieMS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @NotBlank(message = "Booking Id can't be null")
    private String bookingId;

    @NotBlank(message = "Name field can't be Empty")
    private String name;

    @Email(message = "Please enter the valid mail Id")
    @NotBlank(message = "Email Filed can't be blank")
    private String email;

    @NotBlank(message = "seat cannot be blank")
    private int seats;

    @NotBlank(message = "CinemaId can't be blank")
    private String cinemaId;

    @NotBlank(message = "Movie Id can't be blank")
    private String movieId;
}
