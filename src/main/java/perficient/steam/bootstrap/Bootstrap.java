package perficient.steam.bootstrap;


import org.springframework.boot.CommandLineRunner;
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
    ConsoleRepository consoleRepository;
    VideogameRepository videogameRepository;
    UserRepository userRepository;
    SaleRepository saleRepository;

    public Bootstrap(ConsoleRepository consoleRepository, VideogameRepository videogameRepository, UserRepository userRepository, SaleRepository saleRepository) {
        this.consoleRepository = consoleRepository;
        this.videogameRepository = videogameRepository;
        this.userRepository = userRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Administrator admin = new Administrator(1234567,"jose pepe" , "+57 000 0000000" , "M");
        ArrayList<Videogame> games = new ArrayList<Videogame>();
        ArrayList<Console> consoles = new ArrayList<Console>();

        ArrayList<User> users = new ArrayList<User>();
        for(int i = 0 ; i< 4 ; i++ ){

            games.add(new Videogame("game_" + i, new BigDecimal(1000) ,0, "description" , "FPS" , Arrays.asList("Xbox" ,"Play","PC")));
            consoles.add(new Console("console_" + i , new BigDecimal(1000) , 0 , "description"));
            users.add(new User(124356789,"user_" + i ,"109284309123" , "F" ));
        }
        List<Product> products = new ArrayList<>();
        products.addAll(games);
        products.addAll(consoles);


        Sale sale = new Sale(products , users.get(0), LocalDateTime.now());




        videogameRepository.saveAll(games);
        consoleRepository.saveAll(consoles);
        userRepository.saveAll(users);

        saleRepository.save(sale);





    }
}


