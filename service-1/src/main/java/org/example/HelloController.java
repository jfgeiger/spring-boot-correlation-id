package org.example;

import java.text.MessageFormat;
import java.util.logging.Logger;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @PostMapping
  public String greet(@RequestBody final String name) {
    Logger.getLogger(HelloController.class.getName())
        .info(MDC.get(CorrelationIdFilter.CORRELATION_ID));

    return MessageFormat.format("Hello, {0}", name);
  }
}