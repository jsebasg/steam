package perficient.steam.dto;


import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class VideogameDto {
    @NotNull(message = "The name of the Videogame cannot be null")
    private String name;
    @NotNull(message = "The name of the Videogame cannot be null")
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal price;
    @Min(value = 0L, message = "The discount of the Videogame must be positive")
    @Max(value = 1 , message = "The discount of the Videogame must be < 1")
    private double discount;
    @NotNull(message = "The description of the Videogame cannot be null ")
    private String description;
    @NotNull(message = "The category of the Videogame cannot be null")
    private String category ;
    @NotNull(message = "The compatibility of the Videogame cannot be null")

    private String compatibility;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }
}
