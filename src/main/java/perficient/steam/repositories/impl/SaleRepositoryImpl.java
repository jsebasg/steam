package perficient.steam.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import perficient.steam.domain.Sale;
import perficient.steam.repositories.ProductRepository;
import perficient.steam.repositories.SaleRepository;
import perficient.steam.repositories.UserRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class SaleRepositoryImpl implements SaleRepository {
    public SaleRepositoryImpl(){}

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public boolean existsById(Long s) {
        //Integer a = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Product WHERE product.dtype = 'Sale' AND id =" + s, Integer.class);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM sale WHERE id =" + s, Integer.class) == 1;
    }

    @Override
    public void save(Sale sale) {
        jdbcTemplate.update("INSERT INTO sale(id, date, total, user_id , product_id) VALUES (?,?,?,?,? ) ", sale.getId(), sale.getDate(), sale.getTotal(), sale.getUser().getId() , sale.getProducts().getId());
    }

    @Override
    public void update(Sale sale){
        jdbcTemplate.update("UPDATE sale SET  date = ?, total = ? , user_id = ? , product_id = ?  WHERE id ="+ sale.getId(), sale.getDate(), sale.getTotal(), sale.getUser().getId() ,sale.getProducts().getId());
    }

    @Override
    public Optional<Sale> findById(Long id) {
        if(existsById(id)) {
            String sql = "SELECT * FROM sale WHERE id =" + id;
            return Optional.of(jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {

                        Sale sale = new Sale(
                                productRepository.findById(rs.getLong("product_id")).get(),
                                userRepository.findById(rs.getLong("user_id")).get() ,
                                rs.getTimestamp("date").toLocalDateTime());
                        sale.setId(rs.getLong("id"));
                        return sale;
                    }));
        }else{
            return Optional.empty();
        }

    }
    public LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        System.out.println(dateToConvert.toString());
        LocalDateTime ldt = LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
        return ldt;

    }


    @Override
    public List<Sale> findAll() {

        String sql = "SELECT * FROM sale";
        return queryToList(sql);
    }
    @Override
    public List<Sale> findAllPaginated(int actualPage , int totalRowsPerPage){
        int totalPages = (int) (count()/totalRowsPerPage);
        int inicio = actualPage*totalRowsPerPage;
        if(actualPage > totalPages) return new ArrayList<Sale>();
        String sql = "SELECT * FROM sale LIMIT "+totalRowsPerPage+" OFFSET " + inicio;
        return queryToList(sql);
    }

    @Override
    public List<Sale> queryToList(String sql) {
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    Sale sale = new Sale(
                            productRepository.findById(rs.getLong("product_id")).get(),
                            userRepository.findById(rs.getLong("user_id")).get() ,
                            rs.getTimestamp("date").toLocalDateTime());
                    sale.setId(rs.getLong("id"));
                    return sale;
                });
    }


    @Override
    public void deleteById(Long id) {
        if(existsById(id)){
            jdbcTemplate.update("DELETE FROM sale WHERE  id =" + id );
        }
    }

    @Override
    public Long count() {
        Long num = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM sale", Long.class);
        return num;
    }
}
