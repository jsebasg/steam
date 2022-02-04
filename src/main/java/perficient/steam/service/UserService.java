package perficient.steam.service;

import perficient.steam.domain.User;
import perficient.steam.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(UserDto userDto );
    Optional<UserDto> findById(Long id );
    List<UserDto> getAll();
    Boolean deleteById( Long id ) throws Exception;
    Optional<UserDto> update(UserDto userDto, Long id );
}
