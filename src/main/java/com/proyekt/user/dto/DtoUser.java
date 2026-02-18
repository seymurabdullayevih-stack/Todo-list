package com.proyekt.user.dto;

import com.proyekt.user.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {

    private Long id;

    private String userName;

    private List<DtoTodo> todos = new ArrayList<>();
}
