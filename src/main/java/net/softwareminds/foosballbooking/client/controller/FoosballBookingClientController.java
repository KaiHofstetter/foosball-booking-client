package net.softwareminds.foosballbooking.client.controller;

import net.softwareminds.foosballbooking.client.domain.Booking;
import net.softwareminds.foosballbooking.client.domain.BookingList;
import net.softwareminds.foosballbooking.client.oauth2.OAuthClientCredentialClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.ws.rs.core.MediaType;


@Controller
public class FoosballBookingClientController {

  @Autowired
  private OAuthClientCredentialClient oauthClientCredentialClient;
  @Autowired
  private OAuth2RestOperations bookingAuthorizationCodeClient;

  private FoosballBookingClient foosballBookingClient;

  public FoosballBookingClientController() {
    foosballBookingClient = new FoosballBookingClient();
  }

  @RequestMapping(value = "/")
  public ModelAndView allBookings(Map<String, Object> model) throws IOException {
    String clientCredentialAccessToken = oauthClientCredentialClient.getAccessToken();

    BookingList bookingList = foosballBookingClient.getAllBookings(clientCredentialAccessToken);
    model.put("bookings", bookingList.getContent());

    return new ModelAndView("home", model);
  }

  @RequestMapping(value = "/booking", method = RequestMethod.GET)
  public String getBookingPage() {
    // The user should be authenticated before we proceed with the booking form.
    bookingAuthorizationCodeClient.getAccessToken();
    return "book";
  }

  @RequestMapping(value = "/booking", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
  public String formBookingPost(@RequestParam String beginTime, @RequestParam String beginDate, @RequestParam String endTime, @RequestParam String endDate,
                                @RequestParam String comment) throws IOException {
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    LocalDateTime begin = LocalTime.parse(beginTime, timeFormatter).atDate(LocalDate.parse(beginDate, dateFormatter));
    LocalDateTime end = LocalTime.parse(endTime, timeFormatter).atDate(LocalDate.parse(endDate, dateFormatter));

    Booking newBooking = new Booking(begin, end, comment);

    bookingAuthorizationCodeClient.postForLocation(URI.create(FoosballBookingClient.URL_FOOSBALL_BOOKING_SERVICE), newBooking);
    return "redirect:/";
  }
}
