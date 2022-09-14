package com.example.carrentalservice.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCar() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/cars/add");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    void getCar() {
    }

    @Test
    void getCars() {
    }

    @Test
    void updateCarCost() {
    }

    @Test
    void updatePrices() {
    }

    @Test
    void deleteCarById() {
    }

    @Test
    void updateCarInfo() {
    }

    @Test
    void updateCarStatus() {
    }

    @Test
    void getAvailableCars() {
    }

    @Test
    void getCarsByCost() {
    }

    @Test
    void getCarsByClass() {
    }

    @Test
    void getCarsByModel() {
    }
}