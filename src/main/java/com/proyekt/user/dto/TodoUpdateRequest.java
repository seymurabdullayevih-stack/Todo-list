package com.proyekt.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoUpdateRequest {

    private Long id;

    private String title;

    private Boolean completed;

    private String image;
}
