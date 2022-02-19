package perficient.steam.dto;

import perficient.steam.domain.RoleEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ConsoleDto {
    @NotNull(message = "The name of the Console cannot be null")
    private String name;
    @NotNull(message = "The name of the Console cannot be null")
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal price;
    @Min(value = 0L, message = "The discount of the Console must be positive")
    @Max(value = 1 , message = "The discount of the Console must be < 1")
    private double discount;
    @NotNull(message = "The description of the Console cannot be null ")
    private String description;

    private int role;

    private Long id;

    public ConsoleDto() {}

    public ConsoleDto(String name, BigDecimal price, double discount, String description) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.description = description;

    }



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
}
