package perficient.steam.dto;

import perficient.steam.domain.Sale;

import javax.persistence.OneToMany;
import java.util.List;

public class UserDto {
    private int identificationCard;
    private String name;
    private String contactNumber;
    private String gender;

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
