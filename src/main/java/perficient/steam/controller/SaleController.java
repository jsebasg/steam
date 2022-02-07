package perficient.steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perficient.steam.dto.SaleDto;
import perficient.steam.service.serviceImpl.SaleServiceImpl;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/v1/steam/sale" )
public class SaleController  {
    @Autowired
    private SaleServiceImpl saleServiceImpl;


    
    @GetMapping
    public ResponseEntity<List<SaleDto>> getAll() {
        return new ResponseEntity<List<SaleDto>>(saleServiceImpl.getAll() , HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> findById(@PathVariable  long id) {
        Optional<SaleDto> sale = saleServiceImpl.findById(id);

        return sale.isPresent() ? new ResponseEntity<SaleDto>( sale.get(),HttpStatus.OK):new ResponseEntity<SaleDto>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    //@Valid <-
    public ResponseEntity<SaleDto> create(@Valid @RequestBody SaleDto saleDto) {
        return new ResponseEntity<SaleDto>(saleServiceImpl.create(saleDto) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDto> update(@Valid @RequestBody SaleDto saleDto, @PathVariable long id) {
        Optional<SaleDto> sale = saleServiceImpl.findById(id);
        return sale.isPresent() ? new ResponseEntity<SaleDto>( saleServiceImpl.update(saleDto,id).get(),HttpStatus.OK):new ResponseEntity<SaleDto>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id) throws Exception {
        return saleServiceImpl.deleteById(id) ?  new ResponseEntity<Boolean>(  HttpStatus.OK) : new ResponseEntity<Boolean>(  HttpStatus.BAD_REQUEST);
    }
}
