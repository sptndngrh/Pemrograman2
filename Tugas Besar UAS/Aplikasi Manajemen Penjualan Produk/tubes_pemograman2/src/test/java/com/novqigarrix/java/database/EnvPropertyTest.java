package com.novqigarrix.java.database;

import com.novqigarrix.java.database.util.EnvProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnvPropertyTest {

    @Test
    void testGetProperty() {

        String username = EnvProperty.getProperty("MYSQL_USERNAME");
        Assertions.assertNotEquals(null, username);

    }

}
