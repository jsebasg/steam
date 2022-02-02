package perficient.steam.service;

import perficient.steam.domain.User;
import perficient.steam.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(UserDto userDto );
    Optional<User> findById(Long id );
    List<User> getAll();
    Boolean deleteById( Long id ) throws Exception;
    Optional<User> update(UserDto userDto, Long userId );
}
