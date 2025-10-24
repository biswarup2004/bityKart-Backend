package com.bity.bitykart.service;

import com.bity.bitykart.model.User;
import com.bity.bitykart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


      @Autowired
     private UserRepository userRepository;



    public User registeruser(User user) {

        try{
            User newUser = userRepository.save(user);
            System.out.println("user Added Successfully");
            return newUser;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User loginUser(String email, String password) {

       User user= userRepository.findByEmail(email);
       if(user != null &&  user.getPassword().equals(password))
           return user;
       return null;
    }

    public List<User> getAllUser() {
        return  userRepository.findAll();
    }
}
