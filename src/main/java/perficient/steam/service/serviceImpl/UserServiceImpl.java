package perficient.steam.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perficient.steam.domain.User;
import perficient.steam.dto.UserDto;
import perficient.steam.exceptions.UserNotFoundException;
import perficient.steam.repositories.UserRepository;
import perficient.steam.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User create(UserDto userDto) {
        User user = new User(userDto.getIdentificationCard() , userDto.getName() , userDto.getContactNumber() , userDto.getGender());
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) return user;

        throw new UserNotFoundException("USER NOT FOUND EXCEPTION");
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Boolean deleteById(Long id) throws Exception {
        Optional<User> actualUser = userRepository.findById(id);
        if(actualUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> update(UserDto userDto, Long id) {
        Optional<User> actualUser = userRepository.findById(id);
        if(actualUser.isPresent()) {
            actualUser.get().setContactNumber(userDto.getContactNumber());
            actualUser.get().setGender(userDto.getGender());
            actualUser.get().setName(userDto.getName());
            actualUser.get().setIdentificationCard(userDto.getIdentificationCard());
            return actualUser;
        }
        throw  new UserNotFoundException("USER NOT FOUND EXCEPTION");
    }



}
