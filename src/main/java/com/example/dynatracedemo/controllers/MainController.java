package com.example.dynatracedemo.controllers;

import com.example.dynatracedemo.services.SyntheticErrorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    private SyntheticErrorsService syntheticErrorsService;

    @Autowired
    MainController(SyntheticErrorsService syntheticErrorsService) {
        this.syntheticErrorsService = syntheticErrorsService;
    }

    @GetMapping(value = "health-check", produces = MediaType.TEXT_PLAIN_VALUE)
    public String healthCheck() {
        LOG.info("GET: /health-check. result: alive");
        return "alive";
    }

    @GetMapping(value = "start-infinite-loop")
    public HttpStatus startInfiniteLoop() {
        LOG.info("GET: /infinite-loop");
        syntheticErrorsService.startInfiniteLoop();
        return HttpStatus.OK;
    }

    @GetMapping(value = "stop-infinite-loop")
    public HttpStatus stopInfiniteLoop() {
        LOG.info("GET: /stop-infinite-loop");
        syntheticErrorsService.stopInfiniteLoop();
        return HttpStatus.OK;
    }
}

