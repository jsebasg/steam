package perficient.steam.repositories;


import perficient.steam.domain.Console;
import perficient.steam.domain.Sale;
import perficient.steam.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsById(Long s);
    void save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    void update(User user);
    public Long count();
    List<User> findAllPaginated(int actualPage , int totalRowsPerPage);
    List<User> queryToList(String sql);

}
