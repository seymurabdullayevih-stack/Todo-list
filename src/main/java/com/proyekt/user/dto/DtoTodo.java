package com.proyekt.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTodo {

    private Long id;

    private String title;

    private boolean completed;

    private Long userId;
}
