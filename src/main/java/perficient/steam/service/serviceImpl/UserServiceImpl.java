package perficient.steam.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import perficient.steam.domain.RoleEnum;
import perficient.steam.domain.User;
import perficient.steam.dto.UserDto;
import perficient.steam.exceptions.NotFoundException;
import perficient.steam.repositories.UserRepository;
import perficient.steam.repositories.impl.UserRepositoryImpl;
import perficient.steam.service.UserService;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        RoleEnum role = userDto.getRole() == 1? RoleEnum.ADMIN : RoleEnum.USER;
        String passwordhash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()); // password Encryption
        User user = new User(userDto.getIdentificationCard() , userDto.getName() , userDto.getContactNumber() , userDto.getGender() , userDto.getEmail() , role , passwordhash);

        Long id = userRepository.count() + 1;
        user.setId(id);
        userRepository.save(user);
        userDto.setId(id);
        userDto.setPasswordHash(passwordhash);
        return userToUserDto(user);
    }

    @Override
    public Optional<UserDto> update(UserDto userDto, Long id) {
        Optional<User> actualUser = userRepository.findById(id);
        if(actualUser.isPresent()) {
            actualUser.get().setContactNumber(userDto.getContactNumber());
            actualUser.get().setGender(userDto.getGender());
            actualUser.get().setName(userDto.getName());
            actualUser.get().setIdentificationCard(userDto.getIdentificationCard());
            actualUser.get().setRoleEnum(userDto.getRole() == 1? RoleEnum.ADMIN : RoleEnum.USER);
            actualUser.get().setPasswordHash(userDto.getPasswordHash());
            userRepository.update(actualUser.get());
            return Optional.of(userToUserDto(actualUser.get()));
        }
        throw  new NotFoundException("USER NOT FOUND EXCEPTION");
    }

    @Override
    public List<UserDto> getAllByPage(int actualPage, int totalRowsPerPage) {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAllPaginated(actualPage, totalRowsPerPage).forEach(s -> users.add(userToUserDto(s)));
        return users ;
    }


    @Override
    public Optional<UserDto> findById(Long id) {
        Optional<User> user =userRepository.findById(id);
        if(user.isPresent()) return Optional.of(userToUserDto(user.get()));
        throw new NotFoundException("USER NOT FOUND EXCEPTION");
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(s -> users.add(userToUserDto(s)));
        return users ;
    }

    @Override
    public Boolean deleteById(Long id){
        Optional<User> actualUser = userRepository.findById(id);
        if(actualUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException("USER NOT FOUND EXCEPTION");
    }

    public UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setGender(user.getGender());
        userDto.setIdentificationCard(user.getIdentificationCard());
        userDto.setName(user.getName());
        userDto.setRole(user.getRoleEnum().equals("ADMIN")? 0 : 1 );
        userDto.setPasswordHash(user.getPasswordHash());
        return userDto;
    }
    @Override
    public User findByEmail(String email){
        return userRepository.getByEmail(email);
    }
}
