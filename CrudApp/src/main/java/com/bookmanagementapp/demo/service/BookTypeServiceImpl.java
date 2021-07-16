package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.repository.BookTypeRepo;
import com.bookmanagementapp.demo.model.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeRepo bookTypeRepo;

    @Override
    public List<BookType> findBookTypeList() {
        return bookTypeRepo.findAllBookType();
    }

    @Override
    public BookType getSingleBookType(Long id) {
        return bookTypeRepo.getOne(id);
    }
}
