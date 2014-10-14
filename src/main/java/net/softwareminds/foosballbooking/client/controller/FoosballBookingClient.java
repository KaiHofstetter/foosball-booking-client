package net.softwareminds.foosballbooking.client.controller;

import net.softwareminds.foosballbooking.client.domain.Booking;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
}
