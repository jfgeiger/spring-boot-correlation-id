package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

  @PostMapping
  public CompletableFuture<String> greet(@RequestBody final String name) {
    final var correlationId = MDC.get(CorrelationIdFilter.CORRELATION_ID);
    Logger.getLogger(HelloController.class.getName())
        .info(correlationId);

    return HttpClient.newHttpClient()
        .sendAsync(HttpRequest.newBuilder()
            .uri(URI.create("http://service-1:18080"))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(CorrelationIdFilter.CORRELATION_ID, correlationId)
            .POST(BodyPublishers.ofString(name))
            .build(), BodyHandlers.ofString(StandardCharsets.UTF_8))
        .thenApplyAsync(HttpResponse::body);
  }
}