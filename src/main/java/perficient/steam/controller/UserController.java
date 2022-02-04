package perficient.steam.controller;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perficient.steam.dto.UserDto;
import perficient.steam.service.serviceImpl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/v1/steam/user" )
public class UserController{
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<List<UserDto>>(userServiceImpl.getAll() , HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable  long id) {
        Optional<UserDto> user = userServiceImpl.findById(id);
        return user.isPresent() ? new ResponseEntity<UserDto>( user.get(),HttpStatus.OK):new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<UserDto>(userServiceImpl.create(userDto) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable long id) {
        Optional<UserDto> user = userServiceImpl.findById(id);
        return user.isPresent() ? new ResponseEntity<UserDto>( userServiceImpl.update(userDto,id).get(),HttpStatus.OK):new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id) throws Exception {
        return userServiceImpl.deleteById(id) ?  new ResponseEntity<Boolean>(  HttpStatus.OK) : new ResponseEntity<Boolean>(  HttpStatus.BAD_REQUEST);
    }
}
