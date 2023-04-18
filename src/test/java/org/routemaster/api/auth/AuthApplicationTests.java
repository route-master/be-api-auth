package org.routemaster.api.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.yml")
class AuthApplicationTests {

    final TempRestController tempRestController;

    @Autowired
    public AuthApplicationTests(TempRestController tempRestController) {
        this.tempRestController = tempRestController;
    }
    @Test
    void contextLoads() {
    }

    @Test
    void profileLoads() {
        assertEquals(tempRestController.temp(), "test");
    }
}
