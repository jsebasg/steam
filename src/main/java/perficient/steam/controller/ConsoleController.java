package perficient.steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perficient.steam.dto.ConsoleDto;
import perficient.steam.service.serviceImpl.ConsoleServiceImpl;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/v1/steam/console" )
public class ConsoleController{
    @Autowired
    ConsoleServiceImpl consoleServiceImpl;
    //int actualPage , int maxNumber , int totalPages

    @GetMapping
    public ResponseEntity<List<ConsoleDto>> getAll() {
        return new ResponseEntity<List<ConsoleDto>>(consoleServiceImpl.getAll() , HttpStatus.OK );
    }
    @GetMapping("/page/{num}")
    public ResponseEntity<List<ConsoleDto>> getAllByPage(@PathVariable  int num) {
        return new ResponseEntity<List<ConsoleDto>>(consoleServiceImpl.getAllByPage(num - 1 , 3) , HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsoleDto> findById(@PathVariable  long id) {
        Optional<ConsoleDto> console = consoleServiceImpl.findById(id);
        return console.isPresent() ? new ResponseEntity<ConsoleDto>( console.get(),HttpStatus.OK):new ResponseEntity<ConsoleDto>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<ConsoleDto> create(@Valid @RequestBody ConsoleDto consoleDto) {
        return new ResponseEntity<ConsoleDto>(consoleServiceImpl.create(consoleDto) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<ConsoleDto> update(@Valid @RequestBody ConsoleDto consoleDto, @PathVariable long id) {
        Optional<ConsoleDto> console = consoleServiceImpl.findById(id);
        return console.isPresent() ? new ResponseEntity<ConsoleDto>( consoleServiceImpl.update(consoleDto,id).get(),HttpStatus.OK):new ResponseEntity<ConsoleDto>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity delete(@PathVariable long id) throws Exception {
        return consoleServiceImpl.deleteById(id) ?  new ResponseEntity(  HttpStatus.OK) : new ResponseEntity<Boolean>(  HttpStatus.BAD_REQUEST);
    }
    
}
