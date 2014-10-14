package net.softwareminds.foosballbooking.client.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  @Override
  public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException, org.codehaus.jackson.JsonProcessingException {
    String formattedDateTime = dateTime.format(formatter);
    generator.writeString(formattedDateTime);
  }
}
