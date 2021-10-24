package com.jinku.sync;

import org.jinku.sync.application.bootstrap.ApplicationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class ApplicationTestConfig extends ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("data/db-mode.sql")
                .addScript("data/db-schema.sql")
                .addScript("data/db-data.sql")
                .build();
    }
}
