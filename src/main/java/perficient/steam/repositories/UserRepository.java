package perficient.steam.repositories;

import perficient.steam.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User , Long> {
    public List<User> findByGender(String gender);
}
