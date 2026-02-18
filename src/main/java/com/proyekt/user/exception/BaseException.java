package com.proyekt.user.exception;

public class BaseException extends RuntimeException{

    BaseException(){}

    public BaseException(ErrorMessage errorMessage){

        super(errorMessage.prepareErrorMessage());

    }
}
