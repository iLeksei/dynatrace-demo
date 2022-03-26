package com.example.dynatracedemo.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.Random;

@Slf4j
@Configuration
public class HttpFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        LOG.info("Request filter: " + servletRequest.getRemoteAddr());
        LOG.info("Synthetic lagging...");
        try {
            int random = new Random().nextInt(45000);
            LOG.info("Synthetic lagging " + random + " ms...");
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
