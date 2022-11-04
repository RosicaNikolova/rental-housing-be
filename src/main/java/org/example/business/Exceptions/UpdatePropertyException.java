package org.example.business.Exceptions;

import org.example.controller.DTO.UpdatePropertyRequest;

public class UpdatePropertyException extends RuntimeException{

    public UpdatePropertyException(String message){
        super(message);
    }
}
