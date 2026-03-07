package com.proyekt.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String profileImageUrl;
}
