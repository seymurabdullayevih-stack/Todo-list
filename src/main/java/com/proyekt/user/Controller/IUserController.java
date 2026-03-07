package com.proyekt.user.Controller;

import com.proyekt.user.dto.DtoUser;
import com.proyekt.user.dto.DtoUserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {


    public DtoUser allList(String id, Authentication authentication);

    DtoUserResponse updateField( Long id, DtoUserResponse dtoUserResponse);
}
