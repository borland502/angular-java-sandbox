package com.technohouser.watchyourlan.entities.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * Converter class to convert between Date and String for database persistence. This converter
 * automatically applies to all Date attributes in entities.
 */
@Slf4j
@Converter(autoApply = true)
public class StringToDateConverter implements AttributeConverter<Date, String> {

  /**
   * Converts a Date object to its String representation for storing in the database.
   *
   * @param date the Date object to be converted
   * @return the String representation of the Date object, or null if the date is null
   */
  @Override
  public String convertToDatabaseColumn(Date date) {
    if (date == null) {
      return null;
    }
    return DateFormat.getInstance().format(date);
  }

  /**
   * Converts a String representation of a date to a Date object for entity attribute.
   *
   * @param date the String representation of the date
   * @return the Date object, or null if the date string is null or cannot be parsed
   */
  @Override
  public Date convertToEntityAttribute(String date) {
    if (date == null) {
      return null;
    }
    try {
      return DateFormat.getInstance().parse(date);
    } catch (ParseException e) {
      log.error("Error parsing date", e);
      return null;
    }
  }
}
