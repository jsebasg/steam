package perficient.steam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perficient.steam.domain.Console;
import perficient.steam.domain.Product;
import perficient.steam.domain.Sale;
import perficient.steam.domain.User;
import perficient.steam.dto.SaleDto;
import perficient.steam.repositories.ProductRepository;
import perficient.steam.repositories.SaleRepository;
import perficient.steam.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "/v1/steam/sale" )
public class SaleController  {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public SaleController(SaleRepository saleRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public  ResponseEntity<List<Sale>> getAll() {
        return new ResponseEntity<List<Sale>>((List<Sale>) saleRepository.findAll(), HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> findById(@PathVariable  long id) {
        if(!saleRepository.existsById(id)) return new ResponseEntity<Sale>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Sale>(saleRepository.findById(id).get() , HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Sale> create(@RequestBody SaleDto saleDto) {
        List<Product> products = new ArrayList<>();
        saleDto.getProducts().forEach(s -> products.add(productRepository.findById(s).get() ));
        Sale sale = new Sale( products, userRepository.findById(saleDto.getUser()).get() , LocalDateTime.now() );
        return new ResponseEntity<Sale>(saleRepository.save(sale) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@RequestBody SaleDto saleDto, @PathVariable long id) {
        if(!saleRepository.existsById(id)) return new ResponseEntity<Sale>(HttpStatus.BAD_REQUEST);

        List<Product> products = new ArrayList<>();
        saleDto.getProducts().forEach(s -> products.add(productRepository.findById(s).get() ));
        User user = userRepository.findById(saleDto.getUser()).get();

        Sale actualSale = saleRepository.findById(id).get();
        actualSale.setProducts(products);
        actualSale.setUser(user);
        actualSale.setDate(LocalDateTime.now());

        return new ResponseEntity<Sale>(saleRepository.save(actualSale) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id) {
        if(!saleRepository.existsById(id)) return new ResponseEntity<Boolean>( false, HttpStatus.BAD_REQUEST);
        saleRepository.delete(saleRepository.findById(id).get());
        return new ResponseEntity<Boolean>( true, HttpStatus.OK);
    }
}
