package perficient.steam.repositories;


import perficient.steam.domain.Console;
import perficient.steam.domain.Product;
import perficient.steam.domain.Product;
import perficient.steam.domain.Videogame;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    boolean existsById(Long s);
    void save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    void update(Product product);
    List<Product> findAllPaginated( int actualPage , int totalRowsPerPage);
    List<Product> queryToList(String sql);
}
