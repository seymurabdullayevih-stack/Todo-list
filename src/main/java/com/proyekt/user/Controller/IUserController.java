package com.proyekt.user.Controller;

import com.proyekt.user.dto.DtoUser;
import org.springframework.security.core.Authentication;

public interface IUserController {


    public DtoUser allList(String id, Authentication authentication);
}
