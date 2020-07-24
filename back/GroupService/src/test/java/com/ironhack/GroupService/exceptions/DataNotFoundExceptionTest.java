package com.ironhack.GroupService.exceptions;

import org.junit.jupiter.api.Test;

class DataNotFoundExceptionTest {

    private DataNotFoundException dataNotFoundException;
    @Test
    void constructorTest(){
        dataNotFoundException = new DataNotFoundException("");
    }

}