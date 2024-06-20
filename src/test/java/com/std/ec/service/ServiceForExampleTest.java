package com.std.ec.service;

import com.std.ec.service.impl.ServiceForExample;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceForExampleTest {
    @InjectMocks
    private ServiceForExample serviceForExample;

    @Test
    public void testSumar() {
        int result = serviceForExample.example(1,2);
        assertEquals(3,result);

    }
}
