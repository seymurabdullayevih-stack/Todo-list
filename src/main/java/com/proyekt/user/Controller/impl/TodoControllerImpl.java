package com.proyekt.user.Controller.impl;


import com.proyekt.user.Controller.ITodoController;
import com.proyekt.user.dto.DtoTodo;
import com.proyekt.user.dto.TodoRequest;
import com.proyekt.user.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class TodoControllerImpl implements ITodoController {

    @Autowired
    private ITodoService todoService;


    @GetMapping(path = "/todo")
    @Override
    public List<DtoTodo> allListTodo(Authentication authentication) {

        String userName = authentication.getName();


        return todoService.allListTodo(userName);
    }

    @Override
    @PostMapping(path = "/todos")
    public DtoTodo saveTodo(@RequestBody TodoRequest todoRequest, Authentication authentication) {


        String userName = authentication.getName();

        System.out.println("DtoTodoIU " + todoRequest + " " + " userName" + userName);

        return todoService.saveTodo(todoRequest,userName);
    }

    @Override
    @PutMapping(path = "/update/completed/{id}")
    public DtoTodo updateTodo(@PathVariable(name = "id") Long id,
            @RequestBody TodoRequest todoRequest, Authentication authentication) {

        String userName = authentication.getName();

        return todoService.updateTodo(id,todoRequest,userName);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTodo(@PathVariable(name = "id") Long id, Authentication authentication) {

        String userName = authentication.getName();

        todoService.deleteTodo(id,userName);
    }
}
