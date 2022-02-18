package perficient.steam.repositories;


import perficient.steam.domain.Console;

import java.util.List;
import java.util.Optional;


public interface ConsoleRepository{

    boolean existsById(Long s);
    void save(Console console);
    void update(Console console);
    Optional<Console> findById(Long id);
    List<Console> findAll();
    void deleteById(Long id);
    public Long count();
    List<Console> findAllPaginated( int actualPage , int totalRowsPerPage);
    List<Console> queryToList(String sql);

}
