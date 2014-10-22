package net.softwareminds.foosballbooking.client.controller;

import net.softwareminds.foosballbooking.client.domain.Booking;
import net.softwareminds.foosballbooking.client.oauth2.OAuthAuthorizationCodeClient;
import net.softwareminds.foosballbooking.client.oauth2.AccessTokenResponse;
import net.softwareminds.foosballbooking.client.oauth2.OAuthClientCredentialClient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Controller
public class FoosballBookingClientController {

  private OAuthClientCredentialClient oauthClientCredentialClient;
  private OAuthAuthorizationCodeClient oauthAuthorizationCodeClient;

  private FoosballBookingClient foosballBookingClient;
  private String authorizationCodeAccessToken;

  public FoosballBookingClientController() {
    oauthClientCredentialClient = new OAuthClientCredentialClient();
    oauthAuthorizationCodeClient = new OAuthAuthorizationCodeClient();
    foosballBookingClient = new FoosballBookingClient();
  }

  @RequestMapping(value = "/")
  public ModelAndView allBookings(Map<String, Object> model) throws IOException {
    String clientCredentialAccessToken = oauthClientCredentialClient.getAccessTokenResponse().getAccessToken();

    List<Booking> bookings = foosballBookingClient.getAllBookings(clientCredentialAccessToken);

    model.put("bookings", bookings);

    return new ModelAndView("home", model);
  }

  @RequestMapping(value = "/bookings")
  public String postBooking(Map<String, Object> model) throws IOException {
    return "redirect:" + oauthAuthorizationCodeClient.redirectUriToAuthorizationServer();
  }

  @RequestMapping(value = "/book")
  public String getBookPage() throws IOException {
    return "book";
  }

  @RequestMapping(value = "/booking", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
  public String formBookingPost(@RequestParam String beginTime, @RequestParam String beginDate, @RequestParam String endTime, @RequestParam String endDate,
                                @RequestParam String comment) throws IOException {
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    LocalDateTime begin = LocalTime.parse(beginTime, timeFormatter).atDate(LocalDate.parse(beginDate, dateFormatter));
    LocalDateTime end = LocalTime.parse(endTime, timeFormatter).atDate(LocalDate.parse(endDate, dateFormatter));

    foosballBookingClient.addBooking(new Booking(begin, end, "Hans", comment), authorizationCodeAccessToken);

    String redirectUrl = "http://localhost:8090/foosball-booking-client";
    return "redirect:" + redirectUrl;
  }

  @RequestMapping(value = "/authorizationcallback")
  public String callback(@QueryParam(value = "code") String code, @QueryParam(value = "state") String state) {
    AccessTokenResponse accessTokenResponse = oauthAuthorizationCodeClient.getAccessTokenResponse(code);
    authorizationCodeAccessToken = accessTokenResponse.getAccessToken();

    String redirectUrl = "http://localhost:8090/foosball-booking-client/book";
    return "redirect:" + redirectUrl;
  }
}
