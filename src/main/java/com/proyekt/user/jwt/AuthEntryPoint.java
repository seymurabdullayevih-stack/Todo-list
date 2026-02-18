package com.proyekt.user.jwt;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 403 xetasini 401 e cevririk

//Problem:
//Spring Security-də authentication (kimlik doğrulama) uğursuz olduqda, default olaraq 403 Forbidden status code göndərilir. Amma REST API-də bu düzgün deyil, çünki:
//
//403 = "Sən kimliyin təsdiqlənib, amma icazən yoxdur"
//401 = "Sən kimliyin təsdiqləməmisən"
//Token göndərilmədiyi və ya etibarsız olduğu halda 401 Unauthorized göndərilməlidir.

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
    }
}
