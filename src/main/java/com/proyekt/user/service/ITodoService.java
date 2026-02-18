package com.proyekt.user.service;

import com.proyekt.user.dto.DtoTodo;
import com.proyekt.user.dto.TodoRequest;

import java.util.List;

public interface ITodoService {

    public List<DtoTodo> allListTodo(String userName);

    public DtoTodo saveTodo(TodoRequest todoRequest, String userName);

    public  DtoTodo updateTodo(Long id, TodoRequest todoRequest, String userName);

    public  void deleteTodo(Long id,String username);




}
