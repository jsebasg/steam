package perficient.steam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import perficient.steam.domain.Console;
import perficient.steam.generator.ConsoleGenerator;
import perficient.steam.service.ConsoleService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleService service;

    @Test
    public void testGetAllConsoles() throws Exception{
        this.mockMvc.perform(get("/v1/steam/console"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetConsoleById() throws Exception{
        Console console = ConsoleGenerator.random();
        when(service.findById(console.getId())).thenReturn(Optional.ofNullable(service.consoleToConsoleDto(console)));
        this.mockMvc.perform(get("/v1/steam/console/"+console.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testConsoleCreation() throws Exception{
        String body = (new ObjectMapper()).valueToTree(ConsoleGenerator.randomDto()).toString();
        this.mockMvc.perform(post("/v1/steam/console")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
    }
}
