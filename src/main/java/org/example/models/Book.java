package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Book {
    private int id;
    private String name;
    private String author;
    private int genreId;
    private double price;
    private int quantity;


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genreId + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
