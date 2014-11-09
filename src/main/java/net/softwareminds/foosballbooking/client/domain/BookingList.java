package net.softwareminds.foosballbooking.client.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.softwareminds.foosballbooking.client.util.JsonLocalDateTimeDeserializer;
import net.softwareminds.foosballbooking.client.util.JsonLocalDateTimeSerializer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BookingList {
  private List<Booking> content;

  public BookingList() {
  }

  public BookingList(List<Booking> content) {
    this.content = content;
  }

  public List<Booking> getContent() {
    return content;
  }
}
