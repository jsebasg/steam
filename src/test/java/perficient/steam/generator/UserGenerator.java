package perficient.steam.generator;

import com.github.javafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PutMapping;
import perficient.steam.domain.RoleEnum;
import perficient.steam.domain.Sale;
import perficient.steam.domain.User;
import perficient.steam.dto.UserDto;

public class UserGenerator {
    private static Faker faker = new Faker();
    public static User random(){
        User user = new User();
        user.setId(faker.random().nextLong(100));
        user.setPasswordHash(BCrypt.hashpw(faker.name().firstName(), BCrypt.gensalt()));
        user.setGender("F");
        user.setRoleEnum(faker.random().nextInt(1,0 ) == 1 ? RoleEnum.ADMIN : RoleEnum.USER);
        user.setEmail(faker.name().firstName()+"@mail.com");
        user.setIdentificationCard(faker.random().nextInt(1000000000));
        user.setContactNumber(Integer.toString(faker.random().nextInt(1000000000)));
        return user;

    }
    public static UserDto randomDto(){
        UserDto userDto = new UserDto();
        userDto.setId(faker.random().nextLong(100));
        userDto.setPasswordHash(BCrypt.hashpw(faker.name().firstName(), BCrypt.gensalt()));
        userDto.setGender("F");
        userDto.setRole(faker.random().nextInt(1,0 ));
        userDto.setEmail(faker.name().firstName()+"@mail.com");
        userDto.setIdentificationCard(faker.random().nextInt(1000000000));
        userDto.setContactNumber(Integer.toString(faker.random().nextInt(1000000000)));
        return userDto;


    }
}
