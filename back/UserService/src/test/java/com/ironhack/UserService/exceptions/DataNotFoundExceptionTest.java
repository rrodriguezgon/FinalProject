package com.ironhack.UserService.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataNotFoundExceptionTest {

    private DataNotFoundException dataNotFoundException;

    @Test
    void constructorTest(){
        dataNotFoundException = new DataNotFoundException("");
    }
}