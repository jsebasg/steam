package perficient.steam.bootstrap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import perficient.steam.domain.*;
import perficient.steam.repositories.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import java.util.ArrayList;


@Component
public class Bootstrap implements CommandLineRunner {
    /*ConsoleRepository consoleRepository;
    VideogameRepository videogameRepository;
    UserRepository userRepository;
    SaleRepository saleRepository;

    public Bootstrap(ConsoleRepository consoleRepository, VideogameRepository videogameRepository, UserRepository userRepository, SaleRepository saleRepository) {
        this.consoleRepository = consoleRepository;
        this.videogameRepository = videogameRepository;
        this.userRepository = userRepository;
        this.saleRepository = saleRepository;
    }*/

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        String product = "CREATE TABLE IF NOT EXISTS product (" +
                "dtype varchar(31) NOT NULL," +
                "id int8 NOT NULL," +
                "description varchar(255) NULL," +
                "discount float8 NOT NULL," +
                "name varchar(255) NOT NULL," +
                "price numeric(19, 2) NOT NULL," +
                "category varchar(255) NOT NULL," +
                "compatibility varchar(255) NOT NULL," +
                "CONSTRAINT product_pkey PRIMARY KEY (id))";
        String user = "CREATE TABLE IF NOT EXISTS users (" +
                "id int8 NOT NULL," +
                "contact_number varchar(255) NOT NULL," +
                "email varchar(255) NOT NULL," +
                "gender varchar(255) NOT NULL," +
                "identification_card int4 NOT NULL," +
                "name varchar(255) NOT NULL," +
                "password varchar(255) NOT NULL," +
                "CONSTRAINT uk_email UNIQUE (email)," +
                "CONSTRAINT uk_identification_card UNIQUE (identification_card)," +
                "CONSTRAINT users_pkey PRIMARY KEY (id)" +
                ");";

        String sale = "CREATE TABLE IF NOT EXISTS sale (" +
                "id int8 NOT NULL," +
                "date timestamp NOT NULL," +
                "total numeric(19, 2) NOT NULL," +
                "user_id int8 NOT NULL," +
                "product_id int8 NOT NULL," +
                "CONSTRAINT sales_pkey PRIMARY KEY (id)," +
                "CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)," +
                "CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id)" +
                ");";

        jdbcTemplate.execute(product);
        jdbcTemplate.execute(user);
        jdbcTemplate.execute(sale);


    }
}


