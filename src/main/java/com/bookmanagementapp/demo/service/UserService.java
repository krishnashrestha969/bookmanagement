package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.model.User;

public interface UserService {
    User registerUser(User user);
      Long getCurrentUserId();
      String getCurrentUserName();
      String getCurrentPhoneNumber();
//      void createRoleUser(User user);
}

