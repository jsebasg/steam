package perficient.steam.repositories;


import perficient.steam.domain.Console;
import perficient.steam.domain.User;
import perficient.steam.domain.Videogame;

import java.util.List;
import java.util.Optional;

public interface VideogameRepository  {
    boolean existsById(Long s);
    void save(Videogame videogame);
    Optional<Videogame> findById(Long id);
    List<Videogame> findAll();
    void deleteById(Long id);
    void update(Videogame videogame);
    public Long count();
    List<Videogame> findAllPaginated(int actualPage , int totalRowsPerPage);
    List<Videogame> queryToList(String sql);
}
