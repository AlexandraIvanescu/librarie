package ro.biblioteca.online.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Alexandra Ale on 17/05/2017.
 */

@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^.+@.+\\..+$")
    @Size(min = 10, max = 100)
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Z][-a-zA-Z]+$")
    @Size(min = 3, max = 30)
    private String firstName;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Z][-a-zA-Z]+$")
    @Size(min = 3, max = 30)
    private String lastName;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^07[0-9]{8}$")
    @Size(max = 10)
    private String phoneNumber;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
    private Set<Borrow> borrows;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Set<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(Set<Borrow> borrows) {
        this.borrows = borrows;
    }
}
