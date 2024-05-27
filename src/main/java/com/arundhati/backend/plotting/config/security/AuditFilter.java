package com.arundhati.backend.plotting.config.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Order(3)
@WebFilter(urlPatterns = "/admin/*")
@Slf4j
public class AuditFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    log.info("Audit Filter Invoked...");
    chain.doFilter(request, response);
  }
}