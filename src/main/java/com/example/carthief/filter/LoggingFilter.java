package com.example.carthief.filter;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class LoggingFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    @Override
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        logger.info("Incoming request for: " +servletRequest.getScheme());

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
