package com.flexPerk.flexCore.exception;

public class NotFoundException extends  RuntimeException{

    public NotFoundException(String entityNotFound) {
        super(entityNotFound);
    }
}
