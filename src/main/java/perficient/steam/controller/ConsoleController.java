package perficient.steam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perficient.steam.domain.Console;
import perficient.steam.dto.ConsoleDto;
import perficient.steam.repositories.ConsoleRepository;

import java.util.List;

@RestController
@RequestMapping( "/v1/steam/console" )
public class ConsoleController{
    private final ConsoleRepository consoleRepository;

    public ConsoleController(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Console>> getAll() {
        return new ResponseEntity<List<Console>>((List<Console>) consoleRepository.findAll(), HttpStatus.OK );

    }


    @GetMapping("/{id}")
    public ResponseEntity<Console> findById(@PathVariable long id ) {
        if(!consoleRepository.existsById(id)) return new ResponseEntity<Console>(HttpStatus.BAD_REQUEST );
        return new ResponseEntity<Console>(consoleRepository.findById(id).get() , HttpStatus.OK );
    }


    @PostMapping
    public ResponseEntity<Console> create( @RequestBody ConsoleDto consoleDto ) {
        Console console = new Console(consoleDto.getName() , consoleDto.getPrice() , consoleDto.getDiscount() , consoleDto.getDescription());
        return new ResponseEntity<Console>(consoleRepository.save(console), HttpStatus.OK );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Console> update( @RequestBody ConsoleDto consoleDto, @PathVariable long id ) {
        if(!consoleRepository.existsById(id)) return new ResponseEntity<Console>(HttpStatus.BAD_REQUEST );
        Console actualConsole  = consoleRepository.findById(id).get();
        actualConsole.setDescription(consoleDto.getDescription());
        actualConsole.setDiscount(consoleDto.getDiscount());
        actualConsole.setName(consoleDto.getName());
        actualConsole.setPrice(consoleDto.getPrice());
        return new ResponseEntity<Console>(consoleRepository.save(actualConsole), HttpStatus.OK );
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id ){
        if(!consoleRepository.existsById(id)) return new ResponseEntity<Boolean>( false, HttpStatus.BAD_REQUEST );

        consoleRepository.delete(consoleRepository.findById(id).get());
        return new ResponseEntity<Boolean>( true, HttpStatus.OK );

    }
    
}
