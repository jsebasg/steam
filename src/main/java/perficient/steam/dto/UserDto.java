package perficient.steam.dto;

import perficient.steam.domain.Sale;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


public class UserDto {
    @NotNull(message = "The ID of the user cannot be null")
    private int identificationCard;
    @NotNull(message = "The name of the user cannot be null")
    private String name;
    @NotNull(message = "The contact number of the user cannot be null")
    private String contactNumber;
    @NotNull(message = "The gender of the user cannot be null")
    @Pattern(regexp = "^[MF]" , message = "The gender must be M (male) or F (female) ")
    private String gender;
    @NotNull(message = "The email of the user cannot be null")
    @Pattern(regexp = ".*@.*" , message = "The email must contains the @ caracter")
    private String email;
    @NotNull(message = "The password of the user cannot be null")
    @Size(min = 8 , message = "The length of the passwod must be > 8 ")
    private String password;

    private Long id;

    private int role;

    private String passwordHash;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(int identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
