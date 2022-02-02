package perficient.steam.repositories;

import org.springframework.data.repository.CrudRepository;
import perficient.steam.domain.Videogame;

public interface VideogameRepository extends CrudRepository<Videogame, Long> {
}
