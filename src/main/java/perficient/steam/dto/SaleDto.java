package perficient.steam.dto;

import perficient.steam.domain.Product;
import perficient.steam.domain.User;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleDto {

    private List<Long> products;
    private Long user;

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
