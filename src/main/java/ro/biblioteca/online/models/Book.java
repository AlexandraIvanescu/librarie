package ro.biblioteca.online.models;

import java.util.Date;

/**
 * Created by Alexandra Ale on 25.02.2017.
 */
public class Book {

    private String title;
    private String author;
    private String image;
    private Date release;
    private int pageNumber;
    private Category category;

    public Book(String title, String author, Date release, String image, int pageNumber, Category category) {
        this.title = title;
        this.author = author;
        this.release = release;
        this.image = image;
        this.pageNumber = pageNumber;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
