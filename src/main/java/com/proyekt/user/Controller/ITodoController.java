package com.proyekt.user.Controller;

import com.proyekt.user.dto.DtoTodo;
import com.proyekt.user.dto.TodoRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ITodoController {

    public List<DtoTodo> allListTodo(Authentication authentication);

    DtoTodo saveTodo(TodoRequest todoResponse, Authentication authentication);

    public DtoTodo updateTodo(Long id,TodoRequest todoRequest, Authentication authentication);

    public void deleteTodo(Long id, Authentication authentication);
}
