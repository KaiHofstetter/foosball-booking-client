package net.softwareminds.foosballbooking.client.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.softwareminds.foosballbooking.client.util.JsonLocalDateTimeDeserializer;
import net.softwareminds.foosballbooking.client.util.JsonLocalDateTimeSerializer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Booking {
  private LocalDateTime begin = null;
  private LocalDateTime end = null;

  private String user = null;
  private String comment = null;

  public Booking() {
  }

  public Booking(LocalDateTime begin, LocalDateTime end, String comment) {
    this.begin = begin;
    this.end = end;
    this.user = user;
    this.comment = comment;
  }

  @JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
  public void setBegin(LocalDateTime begin) {
    this.begin = begin;
  }

  @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
  public LocalDateTime getBegin() {
    return begin;
  }

  @JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
  public void setEnd(LocalDateTime end) {
    this.end = end;
  }

  @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
  public LocalDateTime getEnd() {
    return end;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getUser() {
    return user;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getComment() {
    return comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Booking booking = (Booking) o;

    return new EqualsBuilder().append(this.begin, booking.begin)
                              .append(this.end, booking.end)
                              .append(this.comment, booking.comment)
                              .append(this.user, booking.user)
                              .build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(begin).append(end).append(user).append(comment).hashCode();
  }
}
