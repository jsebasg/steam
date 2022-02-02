package perficient.steam.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Videogame extends Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String category ;

    @ElementCollection
    private List<String> compatibility;

    public Videogame() {
    }

    public Videogame(String name, BigDecimal price, double discount, String description, String category, List<String> compatibility) {
        super(name, price, discount, description);
        this.category = category;
        this.compatibility = compatibility;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(List<String> compatibility) {
        this.compatibility = compatibility;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
