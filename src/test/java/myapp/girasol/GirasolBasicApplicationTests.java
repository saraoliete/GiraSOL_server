package myapp.girasol;

import myapp.girasol.api.AppController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GirasolBasicApplicationTests {

	@Test
	void contextLoads() {
            
        AppController homeController = new AppController();
        String result = homeController.info().getBody();
        assertEquals(result, "Welcome to GIRASOL Server");
	}

}
