package com.lcobzaru.bootcamp.socialmediaapp.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @NotNull
    @Size(min = 5, max = 254)
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String gender;

    @NotNull
    private LocalDate birthDate;

    public UserDTO(String firstName, String lastName, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;

    }

    public UserDTO(String firstName, String lastName, String username, String email, String gender, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
    }

}
