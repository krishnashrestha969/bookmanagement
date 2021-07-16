package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.model.BookType;

import java.util.List;

public interface BookTypeService {
    List<BookType> findBookTypeList();
    BookType getSingleBookType(Long id);
}
