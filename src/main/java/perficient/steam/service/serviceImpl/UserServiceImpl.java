package perficient.steam.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perficient.steam.domain.User;
import perficient.steam.dto.UserDto;
import perficient.steam.exceptions.UserNotFoundException;
import perficient.steam.repositories.UserRepository;
import perficient.steam.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        User user = new User(userDto.getIdentificationCard() , userDto.getName() , userDto.getContactNumber() , userDto.getGender() , userDto.getEmail());
        userRepository.save(user);
        userDto.setId(user.getId());
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
            return Optional.of(userToUserDto(actualUser.get()));
        }
        throw  new UserNotFoundException("USER NOT FOUND EXCEPTION");
    }


    @Override
    public Optional<UserDto> findById(Long id) {
        Optional<User> user =userRepository.findById(id);
        if(user.isPresent()) return Optional.of(userToUserDto(user.get()));
        throw new UserNotFoundException("USER NOT FOUND EXCEPTION");
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
        return false;
    }

    private UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setGender(user.getGender());
        userDto.setIdentificationCard(user.getIdentificationCard());
        userDto.setName(user.getName());
        return userDto;
    }

}
