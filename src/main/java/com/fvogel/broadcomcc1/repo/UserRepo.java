package com.fvogel.broadcomcc1.repo;

import com.fvogel.broadcomcc1.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserRepo extends PagingAndSortingRepository<UserDto, Long> {

    long count();
    List<UserNameAndAgeProjection> findAllProjectedBy(Pageable pr);
    List<UserNameAndAgeProjection> findByLastName(Pageable pr, @NotNull String lastName);
    List<UserNameAndAgeProjection> findByAge(Pageable pr, @NotNull Integer age);
    List<UserNameAndAgeProjection> findByLastNameAndAge(Pageable pr, @NotNull String lastName, @NotNull Integer age);

    List<UserNameAndAgeProjection> findAllByOrderByAgeDesc(Pageable pr);
    List<UserNameAndAgeProjection> findAllByOrderByAgeAsc(Pageable pr);
    List<UserNameAndAgeProjection> findAllByOrderByLastNameDesc(Pageable pr);
    List<UserNameAndAgeProjection> findAllByOrderByLastNameAsc(Pageable pr);

    List<UserNameAndAgeProjection> findByLastNameAndAgeOrderByLastNameAsc(Pageable pr, @NotNull String lastName, @NotNull Integer age);
    List<UserNameAndAgeProjection> findByLastNameAndAgeOrderByAgeAsc(Pageable pr, @NotNull String lastName, @NotNull Integer age);
    List<UserNameAndAgeProjection> findByLastNameAndAgeOrderByLastNameDesc(Pageable pr, @NotNull String lastName, @NotNull Integer age);
    List<UserNameAndAgeProjection> findByLastNameAndAgeOrderByAgeDesc(Pageable pr, @NotNull String lastName, @NotNull Integer age);

    List<UserNameAndAgeProjection> findByLastNameOrderByAgeAsc(Pageable pr, @NotNull String lastName);
    List<UserNameAndAgeProjection> findByLastNameOrderByAgeDesc(Pageable pr, @NotNull String lastName);
    List<UserNameAndAgeProjection> findByAgeOrderByLastNameAsc(Pageable pr, @NotNull Integer age);
    List<UserNameAndAgeProjection> findByAgeOrderByLastNameDesc(Pageable pr, @NotNull Integer age);
}