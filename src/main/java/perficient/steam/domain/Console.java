package perficient.steam.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "consoles")
public class Console extends Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    public Console() {
    }

    public Console(String name, BigDecimal price, double discount, String description) {
        super(name, price, discount, description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
