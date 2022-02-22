package perficient.steam.generator;

import com.github.javafaker.Faker;
import perficient.steam.domain.Sale;
import perficient.steam.dto.SaleDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class SaleGenerator {
    private static Faker faker = new Faker();
    public static Sale random(){
        Sale sale = new Sale();
        sale.setId(faker.random().nextLong(100));
        sale.setUser(UserGenerator.random());
        sale.setProducts(ConsoleGenerator.random());
        sale.setDate(LocalDateTime.now() );

        return sale;
    }

    public static SaleDto randomDto(){
        SaleDto saleDto = new SaleDto();
        saleDto.setId(faker.random().nextLong(100));
        saleDto.setUser(UserGenerator.random().getId());
        saleDto.setProducts(ConsoleGenerator.random().getId());
        saleDto.setDate(LocalDateTime.now());

        return saleDto;
    }


}
