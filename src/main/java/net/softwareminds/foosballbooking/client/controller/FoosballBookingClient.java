package net.softwareminds.foosballbooking.client.controller;

import net.softwareminds.foosballbooking.client.domain.Booking;
import net.softwareminds.foosballbooking.client.oauth2.AccessTokenResponse;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class FoosballBookingClient {

  public static final String URL_FOOSBALL_BOOKING_SERVICE = "http://localhost:8080/foosball-booking-service/bookings";

  private final Client client;

  public FoosballBookingClient() {
    client = ClientBuilder.newClient();
 }

  public List<Booking> getAllBookings(String accessToken) {
    return client.target(URL_FOOSBALL_BOOKING_SERVICE)
                 .request(MediaType.APPLICATION_JSON)
                 .header("Authorization", "Bearer " + accessToken)
                 .get(new GenericType<List<Booking>>(){});
  }

  public void addBooking(String accessToken) {
    Booking newBooking = new Booking(LocalDateTime.of(2014, 10, 8, 15, 15), LocalDateTime.of(2014, 10, 8, 15, 45), "Michael", "Here we go aggain!");

    client.target(URL_FOOSBALL_BOOKING_SERVICE)
                 .request(MediaType.APPLICATION_JSON)
                 .header("Authorization", "Bearer " + accessToken)
                 .post(Entity.entity(newBooking, MediaType.APPLICATION_JSON), AccessTokenResponse.class);
  }
}
