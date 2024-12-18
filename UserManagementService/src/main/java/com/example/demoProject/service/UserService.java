package com.example.demoProject.service;

import com.example.demoProject.model.Users;
import com.example.demoProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public List<Users> getAllUsers(){
        return  userRepository.findAll();
    }
    public Users getByUserId(int id){
        return  userRepository.findByUserId(id);
    }
    public Users getByUserName(String name){
        return  userRepository.findByUserName(name);
    }


    public Users userRegister(Users users){
        return  userRepository.save(users);

    }

//    public String userLogin(String userName,String password){
//
//        Users uName=userRepository.findByUserName(userName);
//
//        if(uName!=null && uName.getPassword().equals(password)) {
//            return "login successfully by given the username and password";
//        }
//        else {
//            throw new RuntimeException("username or password invalid");
//        }
//    }
public String userLogin() {

        return "login successfull";
}



    public  Users updateUser(Users user){
        Users users=userRepository.findByUserId(user.userId);
        users.setUserName(user.getUserName());
        users.setEmail(user.getEmail());
        users.setPassword(user.getPassword());
        return  userRepository.save(users);

    }
    public String deleteUser(int id){
        Users users=userRepository.findByUserId(id);
        userRepository.delete(users);
        return "user deleted successfully";
    }



}
