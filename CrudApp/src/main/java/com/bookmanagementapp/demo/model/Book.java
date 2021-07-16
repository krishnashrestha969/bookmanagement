package com.bookmanagementapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "book_table")
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "reg_date")
    private String registerDate;

    @Column(name="Description")
    private String description;

    @Column(name="price")
    private long price;

    @Column(name="image")
    private String image;

    @OneToOne
    private BookType bookType;


//    @Override
//    public String toString() {
//        return "Product [id=" + id + ",  Book Name=" + bookName + ", description="
//                + description +", book Type"+bookType+", price=" + price + ", image="
//                + image + "]";
//    }
}