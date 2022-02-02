package perficient.steam.repositories;

import org.springframework.data.repository.CrudRepository;
import perficient.steam.domain.Sale;

public interface SaleRepository extends CrudRepository<Sale, Long> {
}
