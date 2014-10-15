package net.softwareminds.foosballbooking.client.oauth2;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class AuthorizationServerClient {

  public static final String URL_AUTHORIZATION_SERVICE = "http://localhost:8080/foosball-booking-service/oauth/token";
  private static final String CLIENT_ID = "testClient";
  private static final String CLIENT_SECRET = "secret";

  private Client client;

  public AuthorizationServerClient() {
    client = ClientBuilder.newClient();
  }

  public AccessTokenResponse getAccessToken() {
    Form postContent = new Form();
    postContent.param("grant_type", "client_credentials");

    return client.target(URL_AUTHORIZATION_SERVICE)
                 .register(HttpAuthenticationFeature.basic(CLIENT_ID, CLIENT_SECRET))
                 .request(MediaType.APPLICATION_JSON)
                 .post(Entity.entity(postContent, MediaType.APPLICATION_FORM_URLENCODED_TYPE), AccessTokenResponse.class);
  }

  public AccessTokenResponse getAccessToken(String code) {
    client = ClientBuilder.newClient();

    Form postContent = new Form();
    postContent.param("grant_type", "authorization_code");
    postContent.param("code", code);
    postContent.param("redirect_uri", "http://localhost:8090/foosball-booking-client/authorizationcallback");

    return client.target(URL_AUTHORIZATION_SERVICE)
                 .register(HttpAuthenticationFeature.basic("testClient2", "secret"))
                 .request(MediaType.APPLICATION_JSON)
                 .post(Entity.entity(postContent, MediaType.APPLICATION_FORM_URLENCODED_TYPE), AccessTokenResponse.class);
  }
}
