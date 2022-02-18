package perficient.steam.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perficient.steam.domain.Sale;
import perficient.steam.domain.Product;
import perficient.steam.domain.User;
import perficient.steam.dto.ConsoleDto;
import perficient.steam.dto.SaleDto;
import perficient.steam.exceptions.NotFoundException;
import perficient.steam.repositories.ProductRepository;
import perficient.steam.repositories.SaleRepository;
import perficient.steam.repositories.UserRepository;
import perficient.steam.repositories.impl.SaleRepositoryImpl;
import perficient.steam.service.SaleService;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepositoryImpl saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    public SaleDto create(SaleDto saleDto) {
        Sale sale = new Sale(getProducts(saleDto) , getUser(saleDto) , LocalDateTime.now());
        Long id = saleRepository.count() +1;
        sale.setId(id);
        saleRepository.save(sale);
        saleDto.setId(id);
        saleDto.setTotal(sale.getTotal());
        saleDto.setDate(sale.getDate());
        return saleDto;
    }

    @Override
    public Optional<SaleDto> findById(Long id) {
        Optional<Sale> sale =saleRepository.findById(id);
        if(sale.isPresent()) return Optional.of(saleToSaleDto(sale.get()));
        throw new NotFoundException("SALE NOT FOUND EXCEPTION");
    }

    @Override
    public List<SaleDto> getAll() {
        List<SaleDto> sales = new ArrayList<>();
        saleRepository.findAll().forEach(s -> {

            sales.add(saleToSaleDto(s));
        });
        return sales ;
    }

    @Override
    public List<SaleDto> getAllByPage(int actualPage, int totalRowsPerPage) {
        List<SaleDto> sales = new ArrayList<>();
        saleRepository.findAllPaginated(actualPage, totalRowsPerPage).forEach(s -> sales.add(saleToSaleDto(s)));
        return sales ;
    }

    @Override
    public Boolean deleteById(Long id){
        Optional<Sale> actualSale = saleRepository.findById(id);
        if(actualSale.isPresent()) {
            saleRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException("SALE NOT FOUND EXCEPTION");
    }



    @Override
    public Optional<SaleDto> update(SaleDto saleDto, Long id) {
        Optional<Sale> actualSale = saleRepository.findById(id);
        if(actualSale.isPresent()) {
            actualSale.get().setUser(getUser(saleDto));
            actualSale.get().setUser(getUser(saleDto));
            saleRepository.update(actualSale.get());
            return Optional.of(saleToSaleDto(actualSale.get()));
        }
        throw  new NotFoundException("SALE NOT FOUND EXCEPTION");
    }
    public SaleDto saleToSaleDto(Sale sale){

        SaleDto saleDto = new SaleDto();
        List<Long> products = new ArrayList<>();

        saleDto.setId(sale.getId());
        saleDto.setProducts( sale.getProducts().getId());
        saleDto.setUser(sale.getUser().getId());
        saleDto.setTotal(sale.getTotal());
        saleDto.setDate(sale.getDate());

        return saleDto;
    }

    private Product getProducts(SaleDto saleDto){


        if(productRepository.existsById(saleDto.getProducts())){
            return productRepository.findById(saleDto.getProducts()).get();
        }
        else {
            throw new NotFoundException("PRODUCT NOT FOUND ON SALE");
        }



    }
    private User getUser(SaleDto saleDto){


        User user;
        if(userRepository.existsById(saleDto.getUser())){
            user = userRepository.findById(saleDto.getUser()).get();
        }else {
            throw new NotFoundException("USER NOT FOUND ON SALE");
        }
        return user;
    }

}
