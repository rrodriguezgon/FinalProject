package com.ironhack.PadelFriendsService.exceptions;

public class ServiceNotAccessibleException extends RuntimeException {
    public ServiceNotAccessibleException(String message) {
        super(message);
    }
}
