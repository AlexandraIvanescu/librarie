package ro.librarie.online.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^.+@.+\\..+$")
    @Size(min = 10, max = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 3, max = 30)
    private String firstName;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 3, max = 30)
    private String lastName;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
