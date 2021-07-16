package com.bookmanagementapp.demo.repository;

import com.bookmanagementapp.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT book_name FROM book_table  WHERE id= ?", nativeQuery = true)
    Long getUserId(String name);

}
