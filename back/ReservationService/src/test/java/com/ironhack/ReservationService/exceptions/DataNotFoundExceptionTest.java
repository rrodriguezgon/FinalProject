package com.ironhack.ReservationService.exceptions;

import org.junit.jupiter.api.Test;

class DataNotFoundExceptionTest {

    DataNotFoundException dataNotFoundException;

    @Test
    void constructorTest(){
        dataNotFoundException = new DataNotFoundException("");
    }
}