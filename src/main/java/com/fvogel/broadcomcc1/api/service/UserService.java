package com.fvogel.broadcomcc1.api.service;

import com.fvogel.broadcomcc1.repo.UserNameAndAgeProjection;
import com.fvogel.broadcomcc1.repo.UserRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserNameAndAgeProjection> getNamesAndAges(Optional<String> lastName, Optional<Integer> age,
                                                          Optional<String> sortOn, Optional<String> sortType,
                                                          PageRequest pr) {
        List<UserNameAndAgeProjection> result = null;
        boolean filtering = lastName.isPresent() || age.isPresent();
        boolean sorting = isSorting(sortOn, sortType);

        if (!filtering && !sorting)
            result = userRepo.findAllProjectedBy(pr);

        if (filtering && !sorting)
            result = filteredNotSorted(lastName, age, pr);

        if (!filtering && sorting)
            result = sortedNotFiltered(sortOn.get(), sortType.get(), pr);

        if (filtering && sorting)
            result = filteredAndSorted(lastName, age, sortOn.get(), sortType.get(), pr);

        return result;
    }

    private List<UserNameAndAgeProjection> filteredNotSorted(Optional<String> lastName, Optional<Integer> age,
                                                             Pageable pr){
        List<UserNameAndAgeProjection> result = null;

        if (lastName.isPresent() && age.isPresent())
            result = userRepo.findByLastNameAndAge(pr, lastName.get(), age.get());

        if (lastName.isPresent() && !age.isPresent())
            result = userRepo.findByLastName(pr, lastName.get());

        if (!lastName.isPresent() && age.isPresent())
            result = userRepo.findByAge(pr, age.get());

        return result;
    }

    private List<UserNameAndAgeProjection> sortedNotFiltered(String sortOn, String sortType, Pageable pr) {
        List<UserNameAndAgeProjection> result;

        if (sortType.equalsIgnoreCase("desc"))
            if (sortOn.equalsIgnoreCase("lastname"))
                result = userRepo.findAllByOrderByLastNameDesc(pr);
            else
                result = userRepo.findAllByOrderByAgeDesc(pr);
        else
            if (sortOn.equalsIgnoreCase("lastname"))
                result = userRepo.findAllByOrderByLastNameAsc(pr);
            else
                result = userRepo.findAllByOrderByAgeAsc(pr);

        return result;
    }

    private List<UserNameAndAgeProjection> filteredAndSorted(Optional<String> lastName, Optional<Integer> age,
                                                             String sortOn, String sortType, Pageable pr) {
        List<UserNameAndAgeProjection> result = null;

        if (lastName.isPresent() && age.isPresent()) {
            if (sortType.equalsIgnoreCase("asc"))
                if (sortOn.equalsIgnoreCase("lastname"))
                    result = userRepo.findByLastNameAndAgeOrderByLastNameAsc(pr, lastName.get(), age.get());
                else
                    result = userRepo.findByLastNameAndAgeOrderByAgeAsc(pr, lastName.get(), age.get());
            else
                if (sortOn.equalsIgnoreCase("lastname"))
                    result = userRepo.findByLastNameAndAgeOrderByLastNameDesc(pr, lastName.get(), age.get());
                else
                    result = userRepo.findByLastNameAndAgeOrderByAgeDesc(pr, lastName.get(), age.get());
        }

        if (lastName.isPresent() && !age.isPresent()) {
            if (sortOn.equalsIgnoreCase("age"))
                if (sortType.equalsIgnoreCase("asc"))
                    result = userRepo.findByLastNameOrderByAgeAsc(pr, lastName.get());
                else
                    result = userRepo.findByLastNameOrderByAgeDesc(pr, lastName.get());
            else
                result = userRepo.findByLastName(pr, lastName.get());

        }

        if (!lastName.isPresent() && age.isPresent()) {
            if (sortOn.equalsIgnoreCase("lastname"))
                if (sortType.equalsIgnoreCase("asc"))
                    result = userRepo.findByAgeOrderByLastNameAsc(pr, age.get());
                else
                    result = userRepo.findByAgeOrderByLastNameDesc(pr, age.get());
            else
                result = userRepo.findByAge(pr, age.get());
            }

        return result;
    }

    private boolean isSorting(Optional<String> sortOn, Optional<String> sortType) {
        boolean isValid = false;

        if (sortOn.isPresent() && sortType.isPresent())
            if ((sortOn.get().equalsIgnoreCase("age") || sortOn.get().equalsIgnoreCase("lastname")) &&
                    (sortType.get().equalsIgnoreCase("asc") || sortType.get().equalsIgnoreCase("desc")))
                isValid = true;

        return isValid;
    }

    public Long getCount() {
        return userRepo.count();
    }
}
