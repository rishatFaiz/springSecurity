package com.rishat.spring.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class DataSourceConfiguration {

  EmbeddedDatabase database;

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    database = builder.setType(EmbeddedDatabaseType.HSQL)
        .addScript("db/hsqldb/db.sql")
        .build();
    return database;
  }

  @PostConstruct
  public void shutdown() {
    Optional.ofNullable(database).ifPresent(EmbeddedDatabase::shutdown);
  }

}
