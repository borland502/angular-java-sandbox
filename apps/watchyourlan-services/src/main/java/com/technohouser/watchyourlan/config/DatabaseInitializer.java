package com.technohouser.watchyourlan.config;

import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Component responsible for initializing the database. */
@Component
@Slf4j
public class DatabaseInitializer {

  /** The DataSource used for database connections. */
  private final DataSource dataSource;

  /**
   * Constructs a DatabaseInitializer with the specified DataSource.
   *
   * @param dataSource the DataSource to be used for database connections
   */
  public DatabaseInitializer(@Autowired DataSource dataSource) {
    this.dataSource = dataSource;
  }
}
