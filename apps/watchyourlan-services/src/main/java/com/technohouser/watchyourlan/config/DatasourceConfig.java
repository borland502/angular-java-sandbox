package com.technohouser.watchyourlan.config;

import com.technohouser.watchyourlan.utils.xdg.Paths;
import java.io.IOException;
import java.nio.file.Path;
import javax.sql.DataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/** Configuration class for setting up the DataSource bean. */
@Configuration
public class DatasourceConfig implements InitializingBean {

  /** The driver class name for the DataSource. */
  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  /** The URL for the DataSource. */
  @Value("${spring.datasource.url}")
  private String dataSourceUrl;

  /** The path to the SQLite database. */
  @Value("${sqlitedb.path}")
  private Path sqlitePath;

  /**
   * Creates and configures the DataSource bean.
   *
   * @return the configured DataSource
   * @throws IOException if an I/O error occurs
   */
  @Bean
  public DataSource dataSource() throws IOException {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(dataSourceUrl);
    return dataSource;
  }

  /**
   * Ensures that the SQLite database directory exists and is owned by the current user.
   *
   * @throws Exception if an error occurs
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    Paths.ensureOwnerPath(sqlitePath);
  }
}
