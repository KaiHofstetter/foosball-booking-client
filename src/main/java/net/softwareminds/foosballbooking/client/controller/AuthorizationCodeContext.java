package net.softwareminds.foosballbooking.client.controller;

import net.softwareminds.foosballbooking.client.oauth2.AccessTokenResponse;

public class AuthorizationCodeContext {

  private AccessTokenResponse accessTokenResponse;

  public void setAccessTokenResponse(AccessTokenResponse accessTokenResponse) {
    this.accessTokenResponse = accessTokenResponse;
  }

  public AccessTokenResponse getAccessTokenResponse() {
    return accessTokenResponse;
  }
}
