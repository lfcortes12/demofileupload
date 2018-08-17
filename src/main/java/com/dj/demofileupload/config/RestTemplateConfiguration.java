package com.dj.demofileupload.config;

import com.dj.demofileupload.rest.LoggingRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateConfiguration.class);



	private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
          new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(50000);
        clientHttpRequestFactory.setConnectionRequestTimeout(50000);
        clientHttpRequestFactory.setReadTimeout(50000);
        LOGGER.info("Creando rest template con timeout de {} ms", 50000);
        return clientHttpRequestFactory;
    }

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(getClientHttpRequestFactory()));
		/*restTemplate.setErrorHandler(new ApiManagerErrorHandler());*/
		restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
		return restTemplate;
	}

}
