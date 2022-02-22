package perficient.steam.service;

import perficient.steam.domain.Sale;
import perficient.steam.dto.ConsoleDto;
import perficient.steam.dto.SaleDto;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    SaleDto create(SaleDto saleDto );
    Optional<SaleDto> findById(Long id );
    List<SaleDto> getAll();
    Boolean deleteById( Long id ) throws Exception;
    Optional<SaleDto> update(SaleDto saleDto, Long id );
    List<SaleDto> getAllByPage(int actualPage , int totalRowsPerPage);
    SaleDto saleToSaleDto(Sale sale);
}
