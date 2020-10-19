package com.user.accounts.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DataSourceConfiguration {

	@Value("${spring.datasource.driver}")
	private String DB_DRIVER;

	@Value("${spring.datasource.url}")
	private String jdbcUrl;

	@Value("${spring.datasource.username}")
	private String userNameDB;

	@Value("${spring.datasource.password}")
	private String passwordDB;

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSource = DataSourceBuilder.create();
		dataSource.driverClassName(DB_DRIVER);
		dataSource.username(userNameDB);
		dataSource.password(passwordDB);
		dataSource.url(jdbcUrl);
		return dataSource.build();
	}
}
