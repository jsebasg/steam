package perficient.steam.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import perficient.steam.domain.RoleEnum;
import perficient.steam.domain.Sale;
import perficient.steam.domain.User;
import perficient.steam.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public boolean existsById(Long s) {
        //Integer a = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Product WHERE product.dtype = 'User' AND id =" + s, Integer.class);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE id =" + s, Integer.class) == 1;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users(id, contact_number, email , gender , identification_card , name , role , password_hash) VALUES (?,?,?,?,?,?,?,? ) ", user.getId(), user.getContactNumber(), user.getEmail(), user.getGender() , user.getIdentificationCard() , user.getName() , user.getRoleEnum().equals(RoleEnum.ADMIN) ? 1 : 0, user.getPasswordHash());
    }

    @Override
    public void update(User user){
        jdbcTemplate.update("UPDATE users SET  contact_number = ?, email = ? , gender = ? , identification_card = ? , name =? , role = ? , password_hash = ?  WHERE id ="+ user.getId() , user.getContactNumber(), user.getEmail(), user.getGender() , user.getIdentificationCard() , user.getName() ,  user.getRoleEnum().equals(RoleEnum.ADMIN) ? 1 : 0 , user.getPasswordHash());
    }

    @Override
    public Optional<User> findById(Long id) {
        if(existsById(id)) {
            String sql = "SELECT * FROM users WHERE id =" + id;
            //int identificationCard , String name, String contactNumber, String gender, String email , String password
            return Optional.of(jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        User user = new User(
                                rs.getInt("identification_card") ,
                                rs.getString("name"),
                                rs.getString("contact_number"),
                                rs.getString("gender"),
                                rs.getString("email"),
                                rs.getInt("role") == 1 ? RoleEnum.ADMIN : RoleEnum.USER,
                                rs.getString("password_hash"));
                        user.setId(rs.getLong("id"));
                        return user;

                    }));
        }else{
            return Optional.empty();
        }

    }

    @Override
    public List<User> findAll() {

        String sql = "SELECT * FROM users";
        return queryToList(sql);
    }

    @Override
    public void deleteById(Long id) {
        if(existsById(id)){
            jdbcTemplate.update("DELETE FROM users WHERE  id =" + id );
        }
    }

    @Override
    public Long count() {
        Long num = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Long.class);
        return num;
    }

    @Override
    public List<User> findAllPaginated(int actualPage, int totalRowsPerPage) {
        int totalPages = (int) (count()/totalRowsPerPage);
        int inicio = actualPage*totalRowsPerPage;
        if(actualPage > totalPages) return new ArrayList<User>();
        String sql = "SELECT * FROM users LIMIT "+totalRowsPerPage+" OFFSET " + inicio;
        return queryToList(sql);
    }

    @Override
    public List<User> queryToList(String sql) {
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    User user = new User(
                            rs.getInt("identification_card") ,
                            rs.getString("name"),
                            rs.getString("contact_number"),
                            rs.getString("gender"),
                            rs.getString("email"),
                            rs.getInt("role") == 1 ? RoleEnum.ADMIN : RoleEnum.USER,
                            rs.getString("password_hash"));
                    user.setId(rs.getLong("id"));
                    return user;

                });
    }
    public User getByEmail(String email){
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    if(! rs.wasNull()) {
                        User user = new User(
                                rs.getInt("identification_card"),
                                rs.getString("name"),
                                rs.getString("contact_number"),
                                rs.getString("gender"),
                                rs.getString("email"),
                                rs.getInt("role") == 1 ? RoleEnum.ADMIN : RoleEnum.USER,
                                rs.getString("password_hash"));
                        user.setId(rs.getLong("id"));
                        return user;
                    }
                    else{
                        return null;
                    }
                });
    }
}
