package com.proyekt.user.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest {

    private String title;

    private boolean completed;
}
