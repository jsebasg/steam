package perficient.steam.service;

import perficient.steam.domain.User;
import perficient.steam.dto.SaleDto;
import perficient.steam.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(UserDto userDto );
    Optional<UserDto> findById(Long id );
    List<UserDto> getAll();
    Boolean deleteById( Long id ) throws Exception;
    Optional<UserDto> update(UserDto userDto, Long id );
    List<UserDto> getAllByPage(int actualPage , int totalRowsPerPage);
    User findByEmail(String email);
    UserDto userToUserDto(User user);
}
