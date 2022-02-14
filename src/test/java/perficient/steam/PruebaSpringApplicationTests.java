package perficient.steam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import perficient.steam.dto.ConsoleDto;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PruebaSpringApplicationTests {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void contextLoadsConsole() {
		ConsoleDto consoleDto = new ConsoleDto();
		consoleDto.setId(1L);
		consoleDto.setPrice(BigDecimal.valueOf(1243));
		consoleDto.setDescription("asdasd");
		consoleDto.setName("asdasd");
		consoleDto.setDiscount(0.2);


		try {
			mockMvc.perform(get("/v1/steam/console/1")).andExpect(status().isNotFound());
			System.out.println(mockMvc.perform(get("/v1/steam/console/1")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}

	}

}
