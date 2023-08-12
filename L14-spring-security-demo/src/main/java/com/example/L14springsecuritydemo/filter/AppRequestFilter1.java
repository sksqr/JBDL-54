package com.example.L14springsecuritydemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(101)
public class AppRequestFilter1 extends HttpFilter {
    private static Logger LOGGER = LoggerFactory.getLogger(AppRequestFilter1.class);
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        LOGGER.info("Processing request in AppRequestFilter1");
        filterChain.doFilter(request,response);

    }

}
