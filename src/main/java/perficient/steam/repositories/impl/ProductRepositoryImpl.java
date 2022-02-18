package perficient.steam.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import perficient.steam.domain.Console;
import perficient.steam.domain.Product;
import perficient.steam.domain.Videogame;
import perficient.steam.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsById(Long s) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product WHERE id =" + s, Integer.class) == 1;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Optional<Product> findById(Long id) {
        if(existsById(id)) {
            String sql = "SELECT * FROM product WHERE id =" + id;
            return Optional.of(jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        if(rs.getString("dtype").equals("Console")) {
                            Console console = new Console(
                                    rs.getString("name"),
                                    rs.getBigDecimal("price"),
                                    rs.getDouble("discount"),
                                    rs.getString("description"));
                            console.setId(rs.getLong("id"));
                            return console;
                        }
                        else{
                            Videogame videogame = new Videogame(
                                    rs.getString("name"),
                                    rs.getBigDecimal("price"),
                                    rs.getDouble("discount"),
                                    rs.getString("description"),
                                    rs.getString("category"),
                                    rs.getString("compatibility"));
                            videogame.setId(rs.getLong("id"));
                            return videogame;
                        }
                    }));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> findAllPaginated(int actualPage, int totalRowsPerPage) {
        return null;
    }

    @Override
    public List<Product> queryToList(String sql) {
        return null;
    }
}
