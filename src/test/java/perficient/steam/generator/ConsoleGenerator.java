package perficient.steam.generator;

import com.github.javafaker.Faker;
import perficient.steam.domain.Console;
import perficient.steam.dto.ConsoleDto;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

public class ConsoleGenerator {
    public static Console random(){
        Faker faker = new Faker();

        Console console= new Console();
        console.setId(faker.random().nextLong(100));
        console.setDiscount(0);
        console.setPrice(BigDecimal.valueOf(faker.random().nextInt(200)));
        console.setName(faker.name().firstName());
        console.setDescription(faker.name().fullName());

        return console;

    }
    public static ConsoleDto randomDto(){
        Faker faker = new Faker();

        ConsoleDto consoleDto= new ConsoleDto();
        consoleDto.setId(faker.random().nextLong(100));
        consoleDto.setDiscount(0);
        consoleDto.setPrice(BigDecimal.valueOf(faker.random().nextInt(200)));
        consoleDto.setName(faker.name().firstName());
        consoleDto.setDescription(faker.name().fullName());
        return consoleDto;
    }

}
