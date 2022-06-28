package com.fvogel.broadcomcc1.api.controller;

import com.fvogel.broadcomcc1.api.service.UserService;
import com.fvogel.broadcomcc1.dto.AddressDto;
import com.fvogel.broadcomcc1.dto.UserDto;
import com.fvogel.broadcomcc1.repo.UserNameAndAgeProjection;
import com.fvogel.broadcomcc1.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepo userRepo;

    static UserDto user1;
    static PageRequest pr;

    @BeforeAll
    static void setup() {
        List<AddressDto> addressDtos = Arrays.asList(new AddressDto(), new AddressDto());
        user1 = new UserDto(1L, "Darth", "Vader", 60, addressDtos);
        pr = PageRequest.of(0, 10);
    }

    @Test
    void findAllProjectedByTest() {
        doReturn(Arrays.asList(user1)).when(userRepo).findAllProjectedBy(pr);
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.empty();
        Optional<String> sortType = Optional.empty();

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByLastNameTest() {
        Optional<String> lName = Optional.of("Vader");
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.empty();
        Optional<String> sortType = Optional.empty();
        doReturn(Arrays.asList(user1)).when(userRepo).findByLastName(pr, lName.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByAgeTest() {
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.of(10);
        Optional<String> sortOn = Optional.empty();
        Optional<String> sortType = Optional.empty();
        doReturn(Arrays.asList(user1)).when(userRepo).findByAge(pr, age.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findAllByOrderByAgeDescTest() {
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.of("age");
        Optional<String> sortType = Optional.of("desc");
        doReturn(Arrays.asList(user1)).when(userRepo).findAllByOrderByAgeDesc(pr);

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findAllByOrderByAgeAscTest() {
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.of("age");
        Optional<String> sortType = Optional.of("asc");
        doReturn(Arrays.asList(user1)).when(userRepo).findAllByOrderByAgeAsc(pr);

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findAllByOrderByLastNameDescTest() {
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.of("lastname");
        Optional<String> sortType = Optional.of("desc");
        doReturn(Arrays.asList(user1)).when(userRepo).findAllByOrderByLastNameDesc(pr);

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findAllByOrderByLastNameAscTest() {
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.of("lastname");
        Optional<String> sortType = Optional.of("asc");
        doReturn(Arrays.asList(user1)).when(userRepo).findAllByOrderByLastNameAsc(pr);

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByLastNameAndAgeOrderByLastNameAscTest() {
        Optional<String> lName = Optional.of("Vader");
        Optional<Integer> age = Optional.of(25);
        Optional<String> sortOn = Optional.of("lastname");
        Optional<String> sortType = Optional.of("asc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByLastNameAndAgeOrderByLastNameAsc(pr, lName.get(), age.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByLastNameAndAgeOrderByAgeAscTest() {
        Optional<String> lName = Optional.of("Vader");
        Optional<Integer> age = Optional.of(25);
        Optional<String> sortOn = Optional.of("age");
        Optional<String> sortType = Optional.of("asc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByLastNameAndAgeOrderByAgeAsc(pr, lName.get(), age.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }
    @Test
    void findByLastNameAndAgeOrderByLastNameDescTest() {
        Optional<String> lName = Optional.of("Vader");
        Optional<Integer> age = Optional.of(25);
        Optional<String> sortOn = Optional.of("lastName");
        Optional<String> sortType = Optional.of("desc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByLastNameAndAgeOrderByLastNameDesc(pr, lName.get(), age.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByLastNameAndAgeOrderByAgeDescTest() {
        Optional<String> lName = Optional.of("Vader");
        Optional<Integer> age = Optional.of(25);
        Optional<String> sortOn = Optional.of("age");
        Optional<String> sortType = Optional.of("desc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByLastNameAndAgeOrderByAgeDesc(pr, lName.get(), age.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByLastNameOrderByAgeAscTest() {
        Optional<String> lName = Optional.of("Vader");
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.of("age");
        Optional<String> sortType = Optional.of("asc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByLastNameOrderByAgeAsc(pr, lName.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByLastNameOrderByAgeDescTest() {
        Optional<String> lName = Optional.of("Vader");
        Optional<Integer> age = Optional.empty();
        Optional<String> sortOn = Optional.of("age");
        Optional<String> sortType = Optional.of("Desc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByLastNameOrderByAgeDesc(pr, lName.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByAgeOrderByLastNameAscTest() {
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.of(34);
        Optional<String> sortOn = Optional.of("lastName");
        Optional<String> sortType = Optional.of("Asc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByAgeOrderByLastNameAsc(pr, age.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void findByAgeOrderByLastNameDescTest() {
        Optional<String> lName = Optional.empty();
        Optional<Integer> age = Optional.of(34);
        Optional<String> sortOn = Optional.of("lastName");
        Optional<String> sortType = Optional.of("desc");
        doReturn(Arrays.asList(user1)).when(userRepo).findByAgeOrderByLastNameDesc(pr, age.get());

        List<UserNameAndAgeProjection> response = userService.getNamesAndAges(lName, age, sortOn, sortType, pr);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }
}