package perficient.steam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import perficient.steam.domain.User;
import perficient.steam.generator.UserGenerator;
import perficient.steam.service.UserService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    public void testGetAllUsers() throws Exception{
        this.mockMvc.perform(get("/v1/steam/user"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception{
        User user = UserGenerator.random();
        when(service.findById(user.getId())).thenReturn(Optional.ofNullable(service.userToUserDto(user)));
        this.mockMvc.perform(get("/v1/steam/user/"+user.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUserCreation() throws Exception{
        String body = (new ObjectMapper()).valueToTree(UserGenerator.randomDto()).toString();
        this.mockMvc.perform(post("/v1/steam/user")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
    }
}
