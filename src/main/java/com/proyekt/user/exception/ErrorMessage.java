package com.proyekt.user.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {


    private MessageType messageType;

    private String ofstatic;

    public String prepareErrorMessage(){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(messageType.getMessage());

        if (ofstatic!= null){

            stringBuilder.append(" : " + ofstatic);
        }

        return stringBuilder.toString();
    }
}
