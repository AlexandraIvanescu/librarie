package ro.biblioteca.online.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alexandra Ale on 11/06/2017.
 */

@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date startDate;

    private Date endDate;

    private int bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", nullable = false)
    private Subscriber subscriber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
