package com.solumesl.aims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.solumesl")
@EnableTransactionManagement
@EnableJpaRepositories( basePackages = {
		"com.solumesl.aims.repository" })
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class AimsLicenseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AimsLicenseManagementApplication.class, args);
	}

}
