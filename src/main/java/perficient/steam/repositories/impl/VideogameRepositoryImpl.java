package perficient.steam.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import perficient.steam.domain.User;
import perficient.steam.domain.Videogame;
import perficient.steam.domain.Videogame;
import perficient.steam.repositories.VideogameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VideogameRepositoryImpl implements VideogameRepository  {

    public VideogameRepositoryImpl(){}

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsById(Long s) {
        //Integer a = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Product WHERE product.dtype = 'Videogame' AND id =" + s, Integer.class);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product WHERE product.dtype = 'Videogame' AND id =" + s, Integer.class) == 1;
    }

    @Override
    public void save(Videogame videogame) {
        jdbcTemplate.update("INSERT INTO product(dtype ,id, name, price, discount, description, category , compatibility) VALUES (?,?,?,?,?,?,?,? ) ", "Videogame" ,videogame.getId(), videogame.getName(), videogame.getPrice(), videogame.getDiscount() , videogame.getDescription() , videogame.getCategory() , videogame.getCompatibility());
    }

    @Override
    public void update(Videogame videogame){
        jdbcTemplate.update("UPDATE product SET name = ? , price = ?, discount = ? , description = ? , category = ? ,compatibility =  ?   WHERE id =" + videogame.getId() , videogame.getName(), videogame.getPrice(), videogame.getDiscount() , videogame.getDescription() , videogame.getCategory() , videogame.getCompatibility());
    }

    @Override
    public Optional<Videogame> findById(Long id) {
        if(existsById(id)) {
            String sql = "SELECT * FROM product WHERE product.dtype = 'Videogame' AND id =" + id;
            return Optional.of(jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        Videogame videogame = new Videogame(
                                rs.getString("name"),
                                rs.getBigDecimal("price"),
                                rs.getDouble("discount"),
                                rs.getString("description"),
                                rs.getString("category"),
                                rs.getString("compatibility"));
                        videogame.setId(rs.getLong("id"));
                        return videogame;
                    }));
        }else{
            return Optional.empty();
        }
    }


    @Override
    public List<Videogame> findAll() {
        String sql = "SELECT * FROM product WHERE product.dtype = 'Videogame'";
        return queryToList(sql);
    }

    @Override
    public void deleteById(Long id) {
        if(existsById(id)){
            jdbcTemplate.update("DELETE FROM product WHERE product.dtype = 'Videogame' AND id =" + id );
        }
    }

    @Override
    public Long count() {
        Long num = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product", Long.class);
        System.out.println("entra");
        System.out.println(num);
        return num;
    }

    @Override
    public List<Videogame> findAllPaginated(int actualPage, int totalRowsPerPage) {
        int totalPages = (int) (count()/totalRowsPerPage);
        int inicio = actualPage*totalRowsPerPage;
        if(actualPage > totalPages) return new ArrayList<Videogame>();
        String sql = "SELECT * FROM product WHERE product.dtype = 'Videogame' LIMIT "+totalRowsPerPage+" OFFSET " + inicio;
        return queryToList(sql);
    }

    @Override
    public List<Videogame> queryToList(String sql) {
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    Videogame videogame = new Videogame(
                            rs.getString("name"),
                            rs.getBigDecimal("price"),
                            rs.getDouble("discount"),
                            rs.getString("description"),
                            rs.getString("category"),
                            rs.getString("compatibility"));
                    videogame.setId(rs.getLong("id"));
                    return videogame;
                });
    }

}

