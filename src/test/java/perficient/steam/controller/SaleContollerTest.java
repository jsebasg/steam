package perficient.steam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import perficient.steam.domain.Sale;
import perficient.steam.generator.SaleGenerator;
import perficient.steam.service.SaleService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SaleContollerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleService service;

    @Test
    public void testGetAllSales() throws Exception{
        this.mockMvc.perform(get("/v1/steam/sale"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSaleById() throws Exception{
        Sale sale = SaleGenerator.random();
        when(service.findById(sale.getId())).thenReturn(Optional.ofNullable(service.saleToSaleDto(sale)));
        this.mockMvc.perform(get("/v1/steam/sale/"+sale.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaleCreation() throws Exception{
        String body = (new ObjectMapper()).valueToTree(SaleGenerator.randomDto()).toString();
        this.mockMvc.perform(post("/v1/steam/sale")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
    }
}
