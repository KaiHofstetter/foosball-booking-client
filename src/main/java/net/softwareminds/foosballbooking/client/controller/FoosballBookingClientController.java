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

import javax.ws.rs.QueryParam;


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

  @RequestMapping(value = "/bookings")
  public String postBooking(Map<String, Object> model) throws IOException {
    String redirectUrl = "http://localhost:8080/foosball-booking-service/oauth/authorize?response_type=code&client_id=testClient2&state=xyz&redirect_uri=http%3A%2F%2Flocalhost%3A8090%2Ffoosball-booking-client%2Fauthorizationcallback";
    return "redirect:" + redirectUrl;
  }


  @RequestMapping(value = "/authorizationcallback")
  public String callback(@QueryParam(value = "code") String code, @QueryParam(value = "state") String state){
    AccessTokenResponse accessTokenResponse = authorizationServerClient.getAccessToken(code);
    foosballBookingClient.addBooking(accessTokenResponse.getAccessToken());
    String redirectUrl = "http://localhost:8090/foosball-booking-client";
    return "redirect:" + redirectUrl;
  }
}
