package com.dj.demofileupload.rest;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;


public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
            final ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        log(request, body, response);

        return response;
    }

    private ClientHttpResponse log(final HttpRequest request, final byte[] body, final ClientHttpResponse response) throws IOException {
    		LOGGER.debug("ENDPOINT: {}", request.getURI());
        LOGGER.debug("METHOD: {}", request.getMethod().toString());
        LOGGER.debug("URI: {}", request.getURI().toString());
        LOGGER.debug("REQUEST BODY: {}", new String(body));
        LOGGER.debug("RESPONSE BODY: {}", StreamUtils.copyToString(response.getBody(),UTF_8));
        LOGGER.debug("HTTP STATUS CODE {}", response.getStatusCode());
        LOGGER.debug("STATUS CODE MESSAGE: {}", response.getStatusText());
        return response;
    }

}
