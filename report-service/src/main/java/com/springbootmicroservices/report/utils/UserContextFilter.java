package com.springbootmicroservices.report.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        UserContextHolder.getContext().setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setAccessToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

        LOGGER.info("Report Service | UserContextFilter | doFilter | User Service Incoming Correlation id: {} | Auth Token : {}",
                UserContextHolder.getContext().getCorrelationId(),
                UserContextHolder.getContext().getAccessToken()
        );

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
