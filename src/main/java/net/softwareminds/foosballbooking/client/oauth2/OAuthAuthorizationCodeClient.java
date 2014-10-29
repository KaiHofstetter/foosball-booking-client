package net.softwareminds.foosballbooking.client.oauth2;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.web.util.UriTemplate;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class OAuthAuthorizationCodeClient {

  private static final String CLIENT_ID = "Foosball Booking Read/Write Client";
  private static final String CLIENT_SECRET = "secret";

  private static final String URL_AUTHORIZATION_SERVICE = "http://localhost:8080/foosball-booking-service/oauth/token";
  private static final String REDIRECT_URL_TO_AUTHORIZATION_SERVER =
      "http://localhost:8080/foosball-booking-service/oauth/authorize?response_type=code&client_id={clientId}&state={state]&redirect_uri={redirectUri}";

  private static final String CALLBACK_URL_FOR_AUTHORIZATION_CODE = "http://localhost:8090/foosball-booking-client/authorization-code-callback";

  private Client client;

  private AccessTokenContainer accessTokenContainer = null;

  public OAuthAuthorizationCodeClient() {
    client = ClientBuilder.newClient();
  }

  public boolean hasAuthorizationBeenRequested() {
    return accessTokenContainer != null;
  }

  public String getRedirectUriToAuthorizationServer() {
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("clientId", CLIENT_ID);
    uriVariables.put("state", "xyz");
    uriVariables.put("redirectUri", CALLBACK_URL_FOR_AUTHORIZATION_CODE);

    return new UriTemplate(REDIRECT_URL_TO_AUTHORIZATION_SERVER).expand(uriVariables).toString();
  }

  public void requestAccessTokenResponseByCode(String code) {
    Form postContent = new Form();
    postContent.param("grant_type", "authorization_code");
    postContent.param("code", code);
    postContent.param("redirect_uri", CALLBACK_URL_FOR_AUTHORIZATION_CODE);

    AccessTokenResponse accessTokenResponse = client.target(URL_AUTHORIZATION_SERVICE)
                                                    .register(HttpAuthenticationFeature.basic(CLIENT_ID, CLIENT_SECRET))
                                                    .request(MediaType.APPLICATION_JSON)
                                                    .post(Entity.entity(postContent, MediaType.APPLICATION_FORM_URLENCODED_TYPE), AccessTokenResponse.class);

    accessTokenContainer = new AccessTokenContainer(accessTokenResponse);
  }

  public String getAccessToken() {
    if (accessTokenContainer.hasAccessTokenExpired()) {
      refreshAccessToken();
    }
    return accessTokenContainer.getAccessToken();
  }

  public void refreshAccessToken() {
    Form postContent = new Form();
    postContent.param("grant_type", "refresh_token");
    postContent.param("refresh_token", accessTokenContainer.getRefreshToken());

    AccessTokenResponse accessTokenResponse = client.target(URL_AUTHORIZATION_SERVICE)
                                                    .register(HttpAuthenticationFeature.basic(CLIENT_ID, CLIENT_SECRET))
                                                    .request(MediaType.APPLICATION_JSON)
                                                    .post(Entity.entity(postContent, MediaType.APPLICATION_FORM_URLENCODED_TYPE), AccessTokenResponse.class);

    accessTokenContainer = new AccessTokenContainer(accessTokenResponse);
  }
}
