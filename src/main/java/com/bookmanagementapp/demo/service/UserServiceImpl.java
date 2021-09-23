package com.bookmanagementapp.demo.service;

import com.bookmanagementapp.demo.repository.UserRepository;
import com.bookmanagementapp.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SQLQuery;
import org.hibernate.cfg.Configuration;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
HttpServletRequest request;
//    @Autowired
//    RoleRepo roleRepo;


    @Override
    public User registerUser(User user) {

          User user1 = new User();
          user1.setUsername(user.getUsername());
          BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
          String newPassword = encoder.encode(user.getPassword());
          user1.setPassword(newPassword);
          user1.setEmail(user.getEmail());
          user1.setMobileNumber(user.getMobileNumber());
          user1.setAddress(user.getAddress());
          userRepository.save(user1);

          log.info(" New user Registered with Username " + user1.getUsername() + " and user Email " + user1.getEmail());
          return user1;

    }

//    @Override
//      public void createRoleUser(User user)
//    {
//        //        ---------------------------------------------------------------ForRole Configuration
//        Configuration configuration = new Configuration().configure("./hibernate.cfg.xml");
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        String username=user.getUsername();
//        Long userId=userRepository.getUserId(username);
//        String sql1="INSERT into users_roles(user_id,role_id) values(userId ,2)";
//        SQLQuery query = session.createNativeQuery(sql1);
//
//        session.close();
//    }

//    @Override
//    public Long getCurrentUser() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long uid=user.getUser_id();
//        System.out.println("The user id that is mapped is "+uid);
//        return uid;
//    }

    @Override
    public Long getCurrentUserId() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uname="";
        if (principal instanceof UserDetails) {
             uname = ((UserDetails)principal).getUsername();
        } else {
             uname = principal.toString();
        }
        Configuration configuration = new Configuration().configure("./hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        String sql = "SELECT user_id from users where username=:name ";
        SQLQuery query = session.createSQLQuery(sql);
//        NativeQuery<User> query = session.createSQLQuery(sql);
//        query.addEntity(User.class);
        query.setParameter("name",uname);
        String s=query.getSingleResult().toString();
        Long userId=Long.parseLong(s);
        session.close();
        log.info("User id returned of current username "+uname+" and Id no "+userId);
        return  userId;


    }

    @Override
    public String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uname="";
        if (principal instanceof UserDetails) {
            uname = ((UserDetails)principal).getUsername();
        } else {
            uname = principal.toString();
        }
        log.info("Current Username Returned"+ uname);
        return uname;

    }

    @Override
    public String getCurrentPhoneNumber() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uname="";
        if (principal instanceof UserDetails) {
            uname = ((UserDetails)principal).getUsername();
        } else {
            uname = principal.toString();
        }
        Configuration configuration = new Configuration().configure("./hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        String sql = "SELECT mobile_number from users where username=:name ";
        SQLQuery query = session.createSQLQuery(sql);
//        NativeQuery<User> query = session.createSQLQuery(sql);
//        query.addEntity(User.class);
        query.setParameter("name",uname);
        String s=query.getSingleResult().toString();
           session.close();
        log.info("User phonenumber returned of current username "+uname);
        return  s;
    }
}
