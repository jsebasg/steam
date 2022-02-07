package perficient.steam.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perficient.steam.domain.Videogame;
import perficient.steam.dto.VideogameDto;
import perficient.steam.exceptions.NotFoundException;
import perficient.steam.repositories.VideogameRepository;
import perficient.steam.service.VideogameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideogameServiceImpl implements VideogameService {
    @Autowired
    private VideogameRepository videogameRepository;

    @Override
    public VideogameDto create(VideogameDto videogameDto) {

        Videogame videogame = new Videogame(videogameDto.getName() , videogameDto.getPrice() , videogameDto.getDiscount() , videogameDto.getDescription() ,videogameDto.getCategory() , videogameDto.getCompatibility());
        videogameRepository.save(videogame);
        videogameDto.setId(videogame.getId());
        return videogameToVideogameDto(videogame);
    }



    @Override
    public Optional<VideogameDto> update(VideogameDto videogameDto, Long id) {
        Optional<Videogame> actualVideogame = videogameRepository.findById(id);
        if(actualVideogame.isPresent()) {
            actualVideogame.get().setName(videogameDto.getName());
            actualVideogame.get().setPrice(videogameDto.getPrice());
            actualVideogame.get().setDiscount(videogameDto.getDiscount());
            actualVideogame.get().setDescription(videogameDto.getDescription());
            actualVideogame.get().setCategory(videogameDto.getCategory());
            actualVideogame.get().setCompatibility(videogameDto.getCompatibility());
            return Optional.of(videogameToVideogameDto(actualVideogame.get()));
        }
        throw new NotFoundException("VIDEOGAME NOT FOUND EXCEPTION");
    }

    @Override
    public Optional<VideogameDto> findById(Long id) {
        Optional<Videogame> videogame =videogameRepository.findById(id);
        if(videogame.isPresent()) return Optional.of(videogameToVideogameDto(videogame.get()));
        throw new NotFoundException("CONSOLE NOT FOUND EXCEPTION");
    }

    @Override
    public List<VideogameDto> getAll() {
        List<VideogameDto> videogames = new ArrayList<>();
        videogameRepository.findAll().forEach(s -> videogames.add(videogameToVideogameDto(s)));

        return videogames ;
    }

    @Override
    public Boolean deleteById(Long id){
        Optional<Videogame> actualVideogame = videogameRepository.findById(id);
        if(actualVideogame.isPresent()) {
            videogameRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException("CONSOLE NOT FOUND EXCEPTION");
    }

    private VideogameDto videogameToVideogameDto(Videogame videogame){
        VideogameDto videogameDto = new VideogameDto();
        videogameDto.setId(videogame.getId());
        videogameDto.setDescription(videogame.getDescription());
        videogameDto.setDiscount(videogame.getDiscount());
        videogameDto.setName(videogame.getName());
        videogameDto.setPrice(videogame.getPrice());
        videogameDto.setCompatibility(videogame.getCompatibility());
        videogameDto.setCategory(videogame.getCategory());

        return videogameDto;
    }
}
