package com.proyekt.user.Controller;

import com.proyekt.user.dto.AuthResponse;
import com.proyekt.user.dto.DtoLoginUser;
import com.proyekt.user.dto.DtoRegisterUser;
import com.proyekt.user.dto.RefreshToken;
import com.proyekt.user.model.RefreshTokenRequest;
import com.proyekt.user.model.User;
import org.springframework.http.ResponseEntity;

public interface IAuthController {

    public AuthResponse saveRegisterUser(DtoRegisterUser dtoRegisterUser);

    public AuthResponse loginUser(DtoLoginUser dtoLoginUser);

    public RefreshToken accessTokenSend(RefreshTokenRequest request);
}
