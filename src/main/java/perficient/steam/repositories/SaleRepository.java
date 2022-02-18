package perficient.steam.repositories;


import perficient.steam.domain.Console;
import perficient.steam.domain.Product;
import perficient.steam.domain.Sale;
import perficient.steam.domain.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository  {
    boolean existsById(Long s);
    void save(Sale sale);
    Optional<Sale> findById(Long id);
    List<Sale> findAll();
    void deleteById(Long id);
    void update(Sale sale);
    public Long count();
    List<Sale> findAllPaginated(int actualPage , int totalRowsPerPage);
    List<Sale> queryToList(String sql);
}
