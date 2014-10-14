package net.softwareminds.foosballbooking.client.controller;

import net.softwareminds.foosballbooking.client.domain.Booking;
import net.softwareminds.foosballbooking.client.oauth2.AccessTokenResponse;
import net.softwareminds.foosballbooking.client.oauth2.AuthorizationServerClient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
public class FoosballBookingClientController {

  private AuthorizationServerClient authorizationServerClient;
  private FoosballBookingClient foosballBookingClient;

  public FoosballBookingClientController() {
    authorizationServerClient = new AuthorizationServerClient();
    foosballBookingClient = new FoosballBookingClient();
  }

  @RequestMapping(value = "/")
  public ModelAndView allBookings(Map<String, Object> model) throws IOException {
    AccessTokenResponse accessTokenResponse = authorizationServerClient.getAccessToken();

    List<Booking> bookings = foosballBookingClient.getAllBookings(accessTokenResponse.getAccessToken());

    model.put("bookings", bookings);

    return new ModelAndView("home", model);
  }
}
