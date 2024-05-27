package com.arundhati.backend.plotting.config.security;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Order(1)
@Component
@Slf4j
public class LoggingFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    log.info("Logging Filter Invoked...");
    chain.doFilter(request, response);
  }
}