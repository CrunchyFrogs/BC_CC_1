package com.fvogel.broadcomcc1.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Table(name = "users",
       indexes = {@Index(name = "last_name_idx", columnList = "lastName"),
                  @Index(name = "age_idx", columnList = "age"),
                  @Index(name = "ln_age_idx", columnList = "lastName, age")})
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer age;

    @NotNull
    @Column(columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private ArrayList<AddressDto> addressList;
}

