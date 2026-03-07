package com.proyekt.user.Controller.impl;

import com.proyekt.user.Controller.IUserController;
import com.proyekt.user.dto.DtoUser;
import com.proyekt.user.dto.DtoUserResponse;
import com.proyekt.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/user")
public class UserControllerimpl implements IUserController {

    @Autowired
    private IUserService userService;


    @Override
    @GetMapping(path = "/{id}")
    public DtoUser allList(@PathVariable(name = "id",required = false) String id,
                           Authentication authentication) {

        String userName = authentication.getName();


        return userService.allList(id,userName);
    }
    @Override
    @PatchMapping(path = "/update/{id}")
    public DtoUserResponse updateField(@PathVariable(name = "id") Long id,
                                       @RequestBody DtoUserResponse dtoUserResponse) {



        return userService.updateField(id,dtoUserResponse);

    }
}
