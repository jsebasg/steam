package perficient.steam.service;

import perficient.steam.domain.Console;
import perficient.steam.dto.ConsoleDto;

import java.util.List;
import java.util.Optional;

public interface ConsoleService {
    ConsoleDto create(ConsoleDto consoleDto);
    Optional<ConsoleDto> update(ConsoleDto consoleDto, Long id );
    Optional<ConsoleDto> findById(Long id );
    List<ConsoleDto> getAll();
    Boolean deleteById( Long id ) throws Exception;
}
