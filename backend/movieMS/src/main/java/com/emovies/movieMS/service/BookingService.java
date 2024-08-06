package com.emovies.movieMS.service;

import java.util.List;

import com.emovies.movieMS.entity.Booking;

public interface BookingService {

    Booking bookTicket(Booking booking);
    List<Booking> findBookingbyEmail(String email);
    List<Booking> findBookingbyBookingId(String bookingId);

}
