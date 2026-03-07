package com.proyekt.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRequsetUser {

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String imageUser;
}
