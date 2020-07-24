package com.ironhack.PadelFriendsService.handler;

import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.exceptions.ServiceNotAccessibleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public void DataNotFoundExceptionHandler(DataNotFoundException salesRepServiceDownException, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, salesRepServiceDownException.getMessage());
    }

    @ExceptionHandler(ServiceNotAccessibleException.class)
    public void ServiceNotAccessibleExceptionHandler(ServiceNotAccessibleException salesRepServiceDownException, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GATEWAY_TIMEOUT, salesRepServiceDownException.getMessage());
    }
}
