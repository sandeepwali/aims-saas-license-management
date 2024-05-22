package com.solumesl.aims.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import com.solumesl.aims.saas.adapter.job.manager.JobManager;
import com.solumesl.aims.saas.adapter.job.manager.SimpleJobManager;
import com.solumesl.aims.saas.adapter.retry.listener.AuthenticationRetryListener;

@Configuration
@EnableFeignClients(basePackages = {"com.solumesl"})
@EnableRetry
@EnableAsync
public class CoreConfig {
 
	@Bean
	public JobManager simpleJobManager() {
		return new SimpleJobManager(null, null);
	}
	@Bean
	public AuthenticationRetryListener authenticationRetryListener() {
		return new AuthenticationRetryListener();
	}
	@Bean
	public RetryTemplate dsRetryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.registerListener(authenticationRetryListener());
		return retryTemplate;
	}
}
