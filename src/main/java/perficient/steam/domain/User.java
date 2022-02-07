package perficient.steam.domain;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private int identificationCard;

    @Column
    private String name;

    @Column
    private String contactNumber;

    @Column
    private String gender;

    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy="user" , fetch=FetchType.LAZY)
    private List<Sale> sales;

    @Column
    private String password;

    public User() {
    }

    public User(int identificationCard , String name, String contactNumber, String gender, String email , String password){
        this.contactNumber = contactNumber;
        this.identificationCard = identificationCard;
        this.gender = gender;
        this.name = name ;
        this.email = email;
        this.password  = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
