package org.example;


import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf()
        .disable()
        .authorizeRequests()
        .anyRequest()
        .permitAll()
        .and()
        .addFilterBefore(this.correlationIdFilter(), ChannelProcessingFilter.class);
  }

  @Bean
  protected Filter correlationIdFilter() {
    return new CorrelationIdFilter("service-2");
  }
}