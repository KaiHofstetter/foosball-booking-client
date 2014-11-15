package net.softwareminds.foosballbooking.client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.annotation.Resource;

@Configuration
@EnableOAuth2Client
public class OAuth2RestTemplatesConfig {

  private static final String CLIENT_ID = "Foosball Booking Read/Write Client";
  private static final String CLIENT_SECRET = "secret";

  private static final String ACCESS_TOKEN_URI = "http://localhost:8080/foosball-booking-service/oauth/token";

  private static final String USER_AUTHORIZATION_URI = "http://localhost:8080/foosball-booking-service/oauth/authorize";

  @Resource
  @Qualifier("accessTokenRequest")
  private AccessTokenRequest accessTokenRequest;

  @Bean
  @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
  public OAuth2RestOperations bookingAuthorizationCodeClient() {
    OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(bookingAuthorizationCodeDetails(), new DefaultOAuth2ClientContext(accessTokenRequest));
    oAuth2RestTemplate.setAccessTokenProvider(new AuthorizationCodeAccessTokenProvider());
    return oAuth2RestTemplate;
  }

  public OAuth2ProtectedResourceDetails bookingAuthorizationCodeDetails() {
    AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
    details.setId(CLIENT_ID);
    details.setClientId(CLIENT_ID);
    details.setClientSecret(CLIENT_SECRET);
    details.setAccessTokenUri(ACCESS_TOKEN_URI);
    details.setUserAuthorizationUri(USER_AUTHORIZATION_URI);
    return details;
  }
}
