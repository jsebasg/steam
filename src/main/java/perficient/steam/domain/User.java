package perficient.steam.domain;
import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private int identificationCard;
    private String name;
    private String contactNumber;
    private String gender;

    @OneToMany(mappedBy="user")
    private List<Sale> sales ;





    public User() {
    }



    public User(int identificationCard , String name, String contactNumber, String gender){
        this.contactNumber = contactNumber;
        this.identificationCard = identificationCard;
        this.gender = gender;
        this.name = name ;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(int identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
