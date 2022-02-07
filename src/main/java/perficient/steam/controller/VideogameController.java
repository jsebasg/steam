package perficient.steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perficient.steam.dto.VideogameDto;
import perficient.steam.service.serviceImpl.VideogameServiceImpl;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/v1/steam/videogame" )
public class VideogameController {
    @Autowired
    VideogameServiceImpl videogameServiceImpl;

    @GetMapping
    public ResponseEntity<List<VideogameDto>> getAll() {
        return new ResponseEntity<List<VideogameDto>>(videogameServiceImpl.getAll() , HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideogameDto> findById(@PathVariable  long id) {
        Optional<VideogameDto> videogame = videogameServiceImpl.findById(id);
        return videogame.isPresent() ? new ResponseEntity<VideogameDto>( videogame.get(),HttpStatus.OK):new ResponseEntity<VideogameDto>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<VideogameDto> create(@Valid  @RequestBody VideogameDto videogameDto) {
        return new ResponseEntity<VideogameDto>(videogameServiceImpl.create(videogameDto) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideogameDto> update(@Valid @RequestBody VideogameDto videogameDto, @PathVariable long id) {
        Optional<VideogameDto> videogame = videogameServiceImpl.findById(id);
        return videogame.isPresent() ? new ResponseEntity<VideogameDto>( videogameServiceImpl.update(videogameDto,id).get(),HttpStatus.OK):new ResponseEntity<VideogameDto>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id) throws Exception {
        return videogameServiceImpl.deleteById(id) ?  new ResponseEntity<Boolean>(  HttpStatus.OK) : new ResponseEntity<Boolean>(  HttpStatus.BAD_REQUEST);
    }
}
