package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.repository.BookRepo;
import com.bookmanagementapp.demo.model.Book;
import com.bookmanagementapp.demo.model.BookType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BookTypeService bookTypeService;
    @Override
    public List<Book> findAllBook(String name) {

        if (name != null){
            log.info("Book Searched"+name);
            return bookRepo.searchBookByName(name);
        }
        return bookRepo.findAllBooks();
    }
    @Override
    public List<Book> findBookByType(Long id) {
        log.info("Book was searched with TypeID"+id);
        return bookRepo.findBookByType(id);
    }

    @Override
    public List<Book> bookOrderAsc() {
        log.info("Book Sorted by Ascending Order");
        return bookRepo.bookOrderAsc();
    }

    @Override
    public List<Book> bookOrderDsc() {
        log.info("Book Sorted by Descending Order");
        return bookRepo.bookOrderDsc();
    }

    @Override
    public void createBook( Book book, Long id ,MultipartFile file) {
//        book.getId(),book.getBookName(),book.getRegisterDate(),book.getDescription(),book.getPrice(),bookType
        BookType bookType = bookTypeService.getSingleBookType(id);
        Book book1 = new Book();

        book1.setId(book.getId());
        book1.setBookName(book.getBookName());
        book1.setRegisterDate(book.getRegisterDate());
        book1.setDescription(book.getDescription());
        book1.setPrice(book.getPrice());
        //For image
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {

            book1.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        book1.setBookType(bookType);
        log.info("Book was Created "+bookRepo.save(book1));

    }

    @Override
    public Book getSingle(Long id) {
        log.info("Single book extracted with id= "+id);
        return bookRepo.getOne(id);
    }

    @Override
    public Book updateBook(Book book,Long id, MultipartFile file) {
        BookType bookType = bookTypeService.getSingleBookType(id);
        Book book1 = new Book();

        book1.setId(book.getId());
        book1.setBookName(book.getBookName());
        book1.setRegisterDate(book.getRegisterDate());
        book1.setDescription(book.getDescription());
        book1.setPrice(book.getPrice());
        //For image
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {

            book1.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        book1.setBookType(bookType);
        log.info("Book was updated with Book type"+id+" and with book Name"+book.getBookName());
        return bookRepo.save(book1);
    }





    @Override
    public void deleteBook(Long id) {
        log.info("Book Was Deleted with Id "+id);
        bookRepo.deleteById(id);
    }
}
