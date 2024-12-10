package com.example.bilabonnement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class BilAbonnementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getSamletIndtægt() { //A method with @Test is flagged as a JUnit test case.
        LocalDate fraDato_1 = LocalDate.of(2019, 1, 12);
        LocalDate tilDato_1 = LocalDate.of(2023, 12, 31);
        double sumForventet_1 = 16000.0;

        LocalDate fraDato_2 = LocalDate.of(2024, 12, 01);
        LocalDate tilDato_2 = LocalDate.of(2024, 12, 31);
        double sumForventet_2 = 4000.0;
    }



}
