package com.bookmanagementapp.demo.repository;

import com.bookmanagementapp.demo.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTypeRepo extends JpaRepository<BookType,Long> {

    @Query(value = "SELECT * FROM book_type_table", nativeQuery = true)
    List<BookType> findAllBookType();


}
