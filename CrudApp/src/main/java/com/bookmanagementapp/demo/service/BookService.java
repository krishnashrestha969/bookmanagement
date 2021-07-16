package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    void createBook( Book book, Long id,MultipartFile file);
    Book getSingle(Long id);
    Book updateBook(Book book,Long id,MultipartFile file);
    void deleteBook(Long id);

    //Native
    List<Book> findAllBook(String name);
    List<Book>  findBookByType(Long id);
    List<Book> bookOrderAsc();
    List<Book> bookOrderDsc();




}
