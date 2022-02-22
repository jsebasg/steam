package perficient.steam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import perficient.steam.domain.Videogame;
import perficient.steam.generator.VideogameGenerator;
import perficient.steam.service.VideogameService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VideogameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideogameService service;

    @Test
    public void testGetAllVideogames() throws Exception{
        this.mockMvc.perform(get("/v1/steam/videogame"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetVideogameById() throws Exception{
        Videogame videogame = VideogameGenerator.random();
        when(service.findById(videogame.getId())).thenReturn(Optional.ofNullable(service.videogameToVideogameDto(videogame)));
        this.mockMvc.perform(get("/v1/steam/videogame/"+videogame.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testVideogameCreation() throws Exception{
        String body = (new ObjectMapper()).valueToTree(VideogameGenerator.randomDto()).toString();
        this.mockMvc.perform(post("/v1/steam/videogame")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
    }
}
