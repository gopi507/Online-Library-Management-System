package com.example.demoProject.controller;

import com.example.demoProject.model.Users;
import com.example.demoProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUserDetails")
    public List<Users> getAllUserDetails(){
        return userService.getAllUsers();

    }
    @PostMapping("userLogin/{userName}/{password}")

    public String userLogin(@PathVariable("userName") String userName,@PathVariable("password") String password){

      return userService.userLogin(userName,password);
    }
    @GetMapping("/getUserById/{id}")
    public Users getUserById(@PathVariable("id") int id){
        return  userService.getByUserId(id);
    }
    @GetMapping("/getUserByName/{name}")
    public Users getUserByName(@PathVariable("name") String name){
        return  userService.getByUserName(name);
    }
    @PostMapping("/registerUsers")
    public Users registerUsers(@RequestBody Users user){
        return  userService.userRegister(user);
    }
    @PutMapping("/updateUsers")
    public Users updateUsers(@RequestBody Users users){
        return  userService.updateUser(users);
    }
    @DeleteMapping("/deleteUsers/{id}")
    public String deleteUsers(@PathVariable("id") int id){
        return  userService.deleteUser(id);
    }

}
