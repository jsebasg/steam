package perficient.steam.service;



import perficient.steam.domain.Videogame;
import perficient.steam.dto.UserDto;
import perficient.steam.dto.VideogameDto;

import java.util.List;
import java.util.Optional;

public interface VideogameService{
    VideogameDto create(VideogameDto videogameDto );
    Optional<VideogameDto> findById(Long id );
    List<VideogameDto> getAll();
    Boolean deleteById( Long id ) throws Exception;
    Optional<VideogameDto> update(VideogameDto videogameDto, Long id );
    List<VideogameDto> getAllByPage(int actualPage , int totalRowsPerPage);
    VideogameDto videogameToVideogameDto(Videogame videogame);
}
