package perficient.steam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(mappedBy = "id")
    private List<Product> products;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime date;
    private BigDecimal total;


    public Sale() {
    }

    public Sale(List<Product> products, User user, LocalDateTime date) {
        this.products = products;
        this.user = user;
        this.date = date;
        total = new BigDecimal(0) ;
        products.stream().forEach(i -> total = total.add(i.getPrice()));
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
