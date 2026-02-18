package com.proyekt.user.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    // User Exception-lar
    NO_RECORD_EXIST("1001", "Qeyd tapılmadı"),
    USER_NOT_FOUND("1002", "İstifadəçi tapılmadı"),
    USERNAME_ALREADY_EXISTS("1003", "Bu username artıq istifadə olunur"),
    EMAIL_ALREADY_EXISTS("1004", "Bu email artıq qeydiyyatdan keçib"),

    // Authentication Exception-lar
    INVALID_CREDENTIALS("2001", "Username və ya şifrə səhvdir"),
    INVALID_TOKEN("2002", "Token keçərsizdir"),
    TOKEN_EXPIRED("2003", "Token-in vaxtı keçib"),
    INVALID_REFRESH_TOKEN("2004", "Refresh token keçərsizdir"),

    // Validation Exception-lar
    VALIDATION_ERROR("3001", "Validasiya xətası"),
    REQUIRED_FIELD_MISSING("3002", "Tələb olunan sahə boşdur"),

    // General Exception
    GENERAL_EXCEPTION("9999", "Ümumi problem var"),
    INTERNAL_SERVER_ERROR("9998", "Server xətası");

    private  String message;

    private String code;


    MessageType (String code, String message){

        this.message = message;
        this.code = code;
    }

}
