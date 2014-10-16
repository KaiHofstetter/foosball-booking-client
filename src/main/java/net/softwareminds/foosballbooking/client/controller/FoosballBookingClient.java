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

  public void addBooking(Booking booking, String accessToken) {
    client.target(URL_FOOSBALL_BOOKING_SERVICE)
                 .request(MediaType.APPLICATION_JSON)
                 .header("Authorization", "Bearer " + accessToken)
                 .post(Entity.entity(booking, MediaType.APPLICATION_JSON), AccessTokenResponse.class);
  }
}
