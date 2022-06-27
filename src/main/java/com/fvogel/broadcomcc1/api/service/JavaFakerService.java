package com.fvogel.broadcomcc1.api.service;

import com.fvogel.broadcomcc1.dto.AddressDto;
import com.fvogel.broadcomcc1.dto.UserDto;
import com.fvogel.broadcomcc1.repo.UserRepo;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class JavaFakerService {
    final UserRepo userRepo;
    Faker faker = new Faker();

    // 1M Entries in about 20 minutes
    public JavaFakerService(UserRepo userRepo) {this.userRepo = userRepo;}

    public void makeFakeDbEntries(int numEntries) {

        int counter = 0;
        List<UserDto> userList = new ArrayList<>();

        for(int i = 0; i < numEntries; i++) {
            UserDto u = new UserDto();
            ArrayList<AddressDto> addresses = new ArrayList<>();

            u.setFirstName(faker.name().firstName());
            u.setLastName(faker.name().lastName());
            u.setAge((int) faker.number().numberBetween(10, 92L));

            addresses.add(new AddressDto(faker.address().streetAddressNumber(), faker.address().streetName(),
                    faker.address().city(), faker.address().state(), faker.address().zipCode()));

            addresses.add(new AddressDto(faker.address().streetAddressNumber(), faker.address().streetName(),
                    faker.address().city(), faker.address().state(), faker.address().zipCode()));

            u.setAddressList(addresses);

            if (numEntries < 10000)
                userRepo.save(u);
            else {
                counter++;
                userList.add(u);
            }

            if (counter >= 10000) {
                counter = 0;
                userRepo.saveAll(userList);
                userList = new ArrayList<>();
            }
        }

    }
}
