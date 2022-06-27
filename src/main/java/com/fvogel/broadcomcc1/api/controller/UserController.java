package com.fvogel.broadcomcc1.api.controller;

import com.fvogel.broadcomcc1.api.service.UserService;
import com.fvogel.broadcomcc1.repo.UserNameAndAgeProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserNameAndAgeProjection>> getAllUsers(
            @RequestParam Optional<String> lastName,
            @RequestParam Optional<Integer> age,
            @RequestParam Optional<String> sortOn,
            @RequestParam Optional<String> sortType,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size ) {

        PageRequest pageRequest = PageRequest.of(page.orElse(0), size.orElse(10));

        logger.info("Received get user request lastName = {}, age = {}, sortOn = {}, sortType = {}, page = {}, size = {}",
                lastName.orElse(null), age.orElse(null), sortOn.orElse(null), sortType.orElse(null),
                page.orElse(0), size.orElse(10));


        long startTime = System.currentTimeMillis();
        List<UserNameAndAgeProjection> userList = userService.getNamesAndAges(lastName, age, sortOn, sortType, pageRequest);
        logger.info("Request elapsed time {}ms", System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> getUserTableRowCount() {
        return new ResponseEntity<>(userService.getCount(), HttpStatus.OK);

    }
}
