package perficient.steam.generator;

import com.github.javafaker.Faker;
import perficient.steam.domain.Videogame;
import perficient.steam.dto.VideogameDto;

import java.math.BigDecimal;

public class VideogameGenerator {
    public static Videogame random(){
        Faker faker = new Faker();

        Videogame videogame= new Videogame();
        videogame.setId(faker.random().nextLong(100));
        videogame.setDiscount(0);
        videogame.setPrice(BigDecimal.valueOf(faker.random().nextInt(200)));
        videogame.setName(faker.name().firstName());
        videogame.setDescription(faker.name().fullName());
        videogame.setCategory(faker.name().firstName());
        videogame.setCompatibility(faker.name().lastName());
        return videogame;
    }

    public static VideogameDto randomDto(){
        Faker faker = new Faker();
        VideogameDto videogameDto= new VideogameDto();
        videogameDto.setId(faker.random().nextLong(100));
        videogameDto.setDiscount(0);
        videogameDto.setPrice(BigDecimal.valueOf(faker.random().nextInt(200)));
        videogameDto.setName(faker.name().firstName());
        videogameDto.setDescription(faker.name().fullName());
        videogameDto.setCategory(faker.name().firstName());
        videogameDto.setCompatibility(faker.name().lastName());
        return videogameDto;
    }
}
