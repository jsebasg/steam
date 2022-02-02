package perficient.steam.repositories;

import org.springframework.data.repository.CrudRepository;
import perficient.steam.domain.Product;
import perficient.steam.domain.Videogame;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
