package com.fvogel.broadcomcc1.api.controller;

import com.fvogel.broadcomcc1.api.service.JavaFakerService;
import com.fvogel.broadcomcc1.repo.UserNameAndAgeProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/faker", produces = MediaType.APPLICATION_JSON_VALUE)
public class FakerController {
    final JavaFakerService fakerService;
    public FakerController(JavaFakerService fakerService) {this.fakerService = fakerService;}

    Logger logger = LoggerFactory.getLogger(FakerController.class);

    @GetMapping(value = "/{numItemsToMake}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserNameAndAgeProjection>> generateFakeData(
            @PathVariable Optional<Integer> numItemsToMake) {

        logger.info("Building {} fake data items for the DB",numItemsToMake.orElse(0));

        fakerService.makeFakeDbEntries(numItemsToMake.orElse(0));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
