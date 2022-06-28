package com.fvogel.broadcomcc1.api.controller;

import com.fvogel.broadcomcc1.api.service.ChallengeTwoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/api/v1/sum", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChallengeTwoController {
    final ChallengeTwoService challengeTwoService;
    public ChallengeTwoController(ChallengeTwoService challengeTwoService) {this.challengeTwoService = challengeTwoService;}

    Logger logger = LoggerFactory.getLogger(ChallengeTwoController.class);

    @GetMapping(value = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> runChallengeTwo(@PathVariable Optional<Integer> number) {

        logger.info("Code Challenge 2 - target number is: {}.",number.orElse(2));

        return new ResponseEntity<>(Arrays.asList(challengeTwoService.runCC2(number.orElse(2))), HttpStatus.OK);
    }
}