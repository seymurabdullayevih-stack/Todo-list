package com.proyekt.user.service;

import com.proyekt.user.dto.DtoUser;
import com.proyekt.user.dto.DtoUserResponse;
import org.springframework.security.core.Authentication;

public interface IUserService {


    public DtoUser allList(String id,String userName);

    public void updateProfileImage(Long userId, String imageUrl);

    public DtoUserResponse updateField(Long id, DtoUserResponse dtoUserResponse);
}
