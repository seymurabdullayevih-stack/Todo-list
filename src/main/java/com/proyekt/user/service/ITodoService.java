package com.proyekt.user.service;

import com.proyekt.user.dto.DtoImageDelete;
import com.proyekt.user.dto.DtoTodo;
import com.proyekt.user.dto.TodoRequest;
import com.proyekt.user.dto.TodoUpdateRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ITodoService {

    public List<DtoTodo> allListTodo(String userName);

    public DtoTodo saveTodo(TodoRequest todoRequest, String userName);

    public  DtoTodo updateTodo(Long id, TodoRequest todoRequest, String userName);

    public  void deleteTodo(Long id,String username);

    public void updateTodoImage(Long userId, String imageUrl,Long id);

    public DtoTodo findByIdTodo(Long id,String userName);

    public TodoUpdateRequest updatePost(Long id, TodoUpdateRequest todoUpdateRequest, String userName);
}
