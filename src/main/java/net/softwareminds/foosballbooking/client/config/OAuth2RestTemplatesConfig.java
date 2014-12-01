package net.softwareminds.foosballbooking.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuth2RestTemplatesConfig {

  private static final String ACCESS_TOKEN_URI = "http://localhost:8080/foosball-booking-service/oauth/token";
  private static final String USER_AUTHORIZATION_URI = "http://localhost:8080/foosball-booking-service/oauth/authorize";

  @Autowired
  private OAuth2ClientContext oauth2ClientContext;

  @Bean
  public OAuth2RestOperations bookingAuthorizationCodeClient() {
    AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
    resourceDetails.setId("1");
    resourceDetails.setClientId("Foosball Booking Read/Write Client");
    resourceDetails.setClientSecret("secret");
    resourceDetails.setAccessTokenUri(ACCESS_TOKEN_URI);
    resourceDetails.setUserAuthorizationUri(USER_AUTHORIZATION_URI);

    OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);
    oAuth2RestTemplate.setAccessTokenProvider(new AuthorizationCodeAccessTokenProvider());
    return oAuth2RestTemplate;
  }

  // No session scope needed, since the client credential grant has no user context!
  @Bean
  public OAuth2RestOperations bookingClientCredentialClient() {
    ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
    resourceDetails.setId("2");
    resourceDetails.setClientId("Foosball Booking Read Client");
    resourceDetails.setClientSecret("secret");
    resourceDetails.setAccessTokenUri(ACCESS_TOKEN_URI);

    OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
    oAuth2RestTemplate.setAccessTokenProvider(new ClientCredentialsAccessTokenProvider());
    return oAuth2RestTemplate;
  }
}
