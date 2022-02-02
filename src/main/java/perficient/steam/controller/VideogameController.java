package perficient.steam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perficient.steam.domain.Videogame;
import perficient.steam.repositories.VideogameRepository;

import java.util.List;

@RestController
@RequestMapping( "/v1/steam/videogame" )
public class VideogameController {

    private final VideogameRepository videogameRepository;

    public VideogameController(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }


    @GetMapping
    public ResponseEntity<List<Videogame>> getAll() {
        return new ResponseEntity<List<Videogame>>((List<Videogame>) videogameRepository.findAll(), HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videogame> findById(@PathVariable  long id) {
        if(!videogameRepository.existsById(id)) return new ResponseEntity<Videogame>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Videogame>(videogameRepository.findById(id).get() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Videogame> create(@RequestBody Videogame videogame) {
        return new ResponseEntity<Videogame>(videogameRepository.save(videogame) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videogame> update(@RequestBody Videogame videogame, @PathVariable long id) {
        if(!videogameRepository.existsById(id)) return new ResponseEntity<Videogame>(HttpStatus.BAD_REQUEST);
        Videogame actualVideogame = videogameRepository.findById(id).get();
        actualVideogame.setCategory(videogame.getCategory());
        actualVideogame.setCompatibility(videogame.getCompatibility());
        actualVideogame.setDescription(videogame.getDescription());
        actualVideogame.setDiscount(videogame.getDiscount());
        actualVideogame.setName(videogame.getName());
        actualVideogame.setPrice(videogame.getPrice());
        return new ResponseEntity<Videogame>(videogameRepository.save(actualVideogame) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id) {
        if(!videogameRepository.existsById(id)) return new ResponseEntity<Boolean>( false, HttpStatus.BAD_REQUEST);
        videogameRepository.delete(videogameRepository.findById(id).get());
        return new ResponseEntity<Boolean>( true, HttpStatus.OK);
    }
}
