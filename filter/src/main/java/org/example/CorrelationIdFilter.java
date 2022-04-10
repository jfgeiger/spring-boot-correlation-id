package org.example;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

public class CorrelationIdFilter implements Filter {

  public static final String CORRELATION_ID = "Correlation-ID";

  private final String serviceName;

  public CorrelationIdFilter(final String serviceName) {
    this.serviceName = serviceName;
  }

  @Override
  public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
      final FilterChain filterChain) throws IOException, ServletException {
    final var httpServletRequest = (HttpServletRequest) servletRequest;
    final var correlationId = Optional.ofNullable(httpServletRequest.getHeader(CORRELATION_ID))
        .filter(Predicate.not(String::isBlank))
        .orElseGet(() -> MessageFormat.format("{0}:{1}", this.serviceName, UUID.randomUUID()));
    MDC.put(CORRELATION_ID, correlationId);

    filterChain.doFilter(servletRequest, servletResponse);
  }
}