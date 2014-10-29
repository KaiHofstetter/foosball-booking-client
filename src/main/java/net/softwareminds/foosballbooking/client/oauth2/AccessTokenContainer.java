package net.softwareminds.foosballbooking.client.oauth2;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class AccessTokenContainer {
  private AccessTokenResponse accessTokenResponse = null;
  private long accessTokenResponseTime;

  public AccessTokenContainer(AccessTokenResponse accessTokenResponse) {
    this.accessTokenResponse = accessTokenResponse;
    this.accessTokenResponseTime = MILLISECONDS.toSeconds(System.currentTimeMillis());
  }

  public boolean hasAccessTokenExpired() {
    return accessTokenResponse.getExpiresIn() < secondsSinceAccessTokenResponse();
  }

  private long secondsSinceAccessTokenResponse() {
    return MILLISECONDS.toSeconds(System.currentTimeMillis()) - accessTokenResponseTime;
  }

  public String getAccessToken() {
    return accessTokenResponse.getAccessToken();
  }

  public String getRefreshToken() {
    return accessTokenResponse.getRefreshToken();
  }
}
