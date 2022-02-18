package perficient.steam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import perficient.steam.domain.Console;
import perficient.steam.dto.ConsoleDto;
import perficient.steam.exceptions.NotFoundException;
import perficient.steam.repositories.ConsoleRepository;
import perficient.steam.service.serviceImpl.ConsoleServiceImpl;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
public class ConsoleTest {
    @InjectMocks
    ConsoleServiceImpl consoleServiceImpl;

    @Mock
    ConsoleRepository consoleRepository;

    //@Autowired
    //TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext wac;

    /*@Test
    void createConsoleTest(){
        ConsoleDto consoleDto = new ConsoleDto("",BigDecimal.valueOf(1) , 0 ,"");
        consoleServiceImpl.create(consoleDto);
        verify(consoleRepository).save(any(Console.class));
    }
    @Test
    void consoleIdFoundTest(){
        Console console = new Console("name ",BigDecimal.valueOf(1) , 0 ,"      ");
        Long id = 1L;
        console.setId(id);
        System.out.println(console.getId());
        when(consoleRepository.findById(id)).thenReturn( Optional.of(console));
        ConsoleDto consoleFound = consoleServiceImpl.findById(1L).get();
        assertNotNull(consoleFound);
        //assertEquals(consoleFound.getName() , "name");
        //assertEquals( consoleServiceImpl.consoleToConsoleDto(console) , consoleFound) ;

    }

    @Test
    void consoleIdNotFoundExceptionTest(){
        Long id = 1L ;
        when( consoleRepository.findById( id ) ).thenReturn( Optional.empty() );
        Assertions.assertThrows(NotFoundException.class, () -> {
            consoleServiceImpl.findById( id );
        } );
    }*/

    /*@Test
    void getAllConsolesTest(){
        List<ConsoleDto> consoleDtos = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++){
            consoleServiceImpl.create(new ConsoleDto("name",BigDecimal.valueOf(1) , 0 ," "));
        }
        assertEquals(consoleRepository.count() , 5 );
    }*/

    /*@Test
    void shouldReturnCustomMessage() throws Exception {
        assertThat(
                this.restTemplate.getForObject( "http://localhost:8080/v1/steam/console/10000", String.class ) ).contains(
                "CONSOLE NOT FOUND EXCEPTION" );
    }*/




}
