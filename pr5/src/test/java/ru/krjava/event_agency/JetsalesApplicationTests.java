
package ru.krjava.event_agency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.krjava.event_agency.controller.HomeController;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class JetsalesApplicationTests {

    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

}