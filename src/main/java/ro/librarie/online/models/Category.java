package ro.librarie.online.models;

/**
 * Created by Alexandra Ale on 25.02.2017.
 */
public enum Category {
   ROMANCE("Romance"), MYSTERY("Mystery"), HORROR("Horror"), CHILDRENS("Childrens"), SCIENCE("Science") ;
    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
