package perficient.steam.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perficient.steam.domain.Console;
import perficient.steam.dto.ConsoleDto;
import perficient.steam.exceptions.NotFoundException;
import perficient.steam.repositories.ConsoleRepository;
import perficient.steam.repositories.impl.ConsoleRepositoryImpl;
import perficient.steam.service.ConsoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    @Autowired
    private ConsoleRepositoryImpl consoleRepository;

    public ConsoleServiceImpl(){}

    @Override
    public ConsoleDto create(ConsoleDto consoleDto) {
        Long id = consoleRepository.count()+1;
        Console console = new Console(consoleDto.getName() , consoleDto.getPrice() , consoleDto.getDiscount() , consoleDto.getDescription());
        console.setId(id);
        consoleRepository.save(console);
        consoleDto.setId(id);

        return consoleDto;
    }

    @Override
    public Optional<ConsoleDto> update(ConsoleDto consoleDto, Long id) {
        Optional<Console> actualConsole = consoleRepository.findById(id);
        if(actualConsole.isPresent()) {
            actualConsole.get().setName(consoleDto.getName());
            actualConsole.get().setPrice(consoleDto.getPrice());
            actualConsole.get().setDiscount(consoleDto.getDiscount());
            actualConsole.get().setDescription(consoleDto.getDescription());
            consoleRepository.update(actualConsole.get());
            return Optional.of(consoleToConsoleDto(actualConsole.get()));
        }
        throw new NotFoundException("CONSOLE NOT FOUND EXCEPTION");
    }

    @Override
    public Optional<ConsoleDto> findById(Long id) {
        Optional<Console> console =consoleRepository.findById(id);
        if(console.isPresent()) return Optional.of(consoleToConsoleDto(console.get()));
        throw new NotFoundException("CONSOLE NOT FOUND EXCEPTION");
    }

    @Override
    public List<ConsoleDto> getAll() {
        List<ConsoleDto> consoles = new ArrayList<>();
        consoleRepository.findAll().forEach(s -> consoles.add(consoleToConsoleDto(s)));
        return consoles ;
    }
    @Override
    public List<ConsoleDto> getAllByPage(int actualPage, int totalRowsPerPage) {
        List<ConsoleDto> consoles = new ArrayList<>();
        consoleRepository.findAllPaginated(actualPage, totalRowsPerPage).forEach(s -> consoles.add(consoleToConsoleDto(s)));
        return consoles ;
    }




    @Override
    public Boolean deleteById(Long id){
        Optional<Console> actualConsole = consoleRepository.findById(id);
        if(actualConsole.isPresent()) {
            consoleRepository.deleteById(id);
            return true;
        }

        throw new NotFoundException("CONSOLE NOT FOUND EXCEPTION ");
    }

    public ConsoleDto consoleToConsoleDto(Console console){
        ConsoleDto consoleDto = new ConsoleDto();
        consoleDto.setId(console.getId());
        consoleDto.setDescription(console.getDescription());
        consoleDto.setDiscount(console.getDiscount());
        consoleDto.setName(console.getName());
        consoleDto.setPrice(console.getPrice());
        return consoleDto;
    }


}
