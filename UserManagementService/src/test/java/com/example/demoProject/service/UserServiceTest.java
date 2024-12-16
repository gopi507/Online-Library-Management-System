package com.example.demoProject.service;

import com.example.demoProject.model.Users;
import com.example.demoProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(value = UserService.class)
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Test
    void testGetAllUsers(){
        List<Users> usersList=new ArrayList<>();
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        usersList.add(users);
        when(userRepository.findAll()).thenReturn(usersList);
        assertEquals(usersList,userService.getAllUsers());
    }
    @Test
    void testGetByUserId(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userRepository.findByUserId(1)).thenReturn(users);
        assertEquals(users,userService.getByUserId(1));

    }
    @Test
    void testGetByUserName(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userRepository.findByUserName("gopi")).thenReturn(users);
        assertEquals(users,userService.getByUserName("gopi"));
    }
    @Test
    void testUserRegister(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userRepository.save(users)).thenReturn(users);
        assertEquals(users,userService.userRegister(users));

    }
    @Test
    void testUpdateUser(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userRepository.save(users)).thenReturn(users);
        when((userRepository.findByUserId(1))).thenReturn(users);
        Users updateUser=userService.updateUser(users);
        updateUser.setUserName("gopi s");
        updateUser.setEmail("gopis@gmail.com");
        updateUser.setPassword("gopi123");
        assertNotNull(updateUser);
        assertEquals(users,updateUser);

    }
    @Test
    void testDeleteUser() {
        Users users = new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userRepository.findByUserId(1)).thenReturn(users);
        doNothing().when(userRepository).delete(users);
        assertEquals("user deleted successfully",userService.deleteUser(1));
    }
    @Test
  void testUserLogin(){
       Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
      when(userRepository.save(users)).thenReturn(users);
      when(userRepository.findByUserName("gopi")).thenReturn(users);

      String usernamePassword=userService.userLogin("gopi","gopi12");
      assertEquals("login successfully by given the username and password",usernamePassword);
  }
}
