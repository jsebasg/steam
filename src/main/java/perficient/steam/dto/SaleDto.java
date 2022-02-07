package perficient.steam.dto;



import javax.validation.constraints.*;


import java.util.List;

public class SaleDto {
    @NotNull(message = "The product list on the sale cannot be null")
    @Size(min = 1 , message =  "The sale must contain products" )
    private List<Long> products;
    @NotNull(message = "the user in the sale cannot be null ")
    private Long user;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
