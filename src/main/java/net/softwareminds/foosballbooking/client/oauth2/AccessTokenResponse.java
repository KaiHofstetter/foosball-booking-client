package net.softwareminds.foosballbooking.client.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenResponse {

  @JsonProperty(value = "access_token")
  private String accessToken;
  @JsonProperty(value = "token_type")
  private String tokenType;
  @JsonProperty(value = "expires_in")
  private long expiresIn;
  @JsonProperty(value = "scope")
  private String scope;

  public AccessTokenResponse() {
  }

  public AccessTokenResponse(String accessToken, String tokenType, long expiresIn, String scope) {
    this.accessToken = accessToken;
    this.tokenType = tokenType;
    this.expiresIn = expiresIn;
    this.scope = scope;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }
}
