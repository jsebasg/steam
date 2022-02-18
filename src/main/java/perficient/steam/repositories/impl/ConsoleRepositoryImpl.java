package perficient.steam.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import perficient.steam.domain.Console;
import perficient.steam.repositories.ConsoleRepository;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ConsoleRepositoryImpl implements ConsoleRepository {

    public ConsoleRepositoryImpl(){}

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsById(Long s) {
        //Integer a = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Product WHERE product.dtype = 'Console' AND id =" + s, Integer.class);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product WHERE product.dtype = 'Console' AND id =" + s, Integer.class) == 1;
    }

    @Override
    public void save(Console console) {
        jdbcTemplate.update("INSERT INTO product(dtype ,id, name, price, discount, description, category) VALUES (?,?,?,?,?,?,?) ", "Console" ,console.getId(), console.getName(), console.getPrice(), console.getDiscount() , console.getDescription() , null);
    }

    @Override
    public void update(Console console){
        jdbcTemplate.update("UPDATE product SET name = ? , price = ?, discount = ? , description = ? , category = ? WHERE id =" + console.getId() , console.getName(), console.getPrice(), console.getDiscount() , console.getDescription() , null);
    }

    @Override
    public Optional<Console> findById(Long id) {
        if(existsById(id)) {
            String sql = "SELECT * FROM product WHERE product.dtype = 'Console' AND id =" + id;
            return Optional.of(jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        Console console = new Console(
                                rs.getString("name"),
                                rs.getBigDecimal("price"),
                                rs.getDouble("discount"),
                                rs.getString("description"));
                        console.setId(rs.getLong("id"));
                        return console;
                    }));
        }else{
            return Optional.empty();
        }

    }


    @Override
    public List<Console> findAll() {

        String sql = "SELECT * FROM product WHERE product.dtype = 'Console'";
        return queryToList(sql);
    }

    @Override
    public void deleteById(Long id) {
        if(existsById(id)){
            jdbcTemplate.update("DELETE FROM product WHERE product.dtype = 'Console' AND id =" + id );
        }
    }

    @Override
    public Long count() {
        Long num = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product", Long.class);

        return num;
    }
    @Override
    public List<Console> findAllPaginated( int actualPage , int totalRowsPerPage){
        int totalPages = (int) (count()/totalRowsPerPage);
        int inicio = actualPage*totalRowsPerPage;

        if(actualPage > totalPages) return new ArrayList<Console>();
        String sql = "SELECT * FROM product WHERE product.dtype = 'Console' LIMIT "+totalRowsPerPage+" OFFSET " + inicio;
        //SELECT * FROM table limit 100, 200""
        return queryToList(sql);
    }

    public List<Console> queryToList(String sql){
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    Console console = new Console(
                            rs.getString("name"),
                            rs.getBigDecimal("price"),
                            rs.getDouble("discount"),
                            rs.getString("description"));
                    console.setId(rs.getLong("id"));
                    return console;
                });
    }
}
