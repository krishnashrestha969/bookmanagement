package com.bookmanagementapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "bookType_table")
@AllArgsConstructor
@NoArgsConstructor
public class BookType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blid;

    @Column(name = "book_type")
    private String bookType;
}
