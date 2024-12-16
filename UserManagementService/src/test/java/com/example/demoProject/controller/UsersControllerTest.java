package com.example.demoProject.controller;

import com.example.demoProject.model.Users;
import com.example.demoProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest( value =UsersController.class)
public class UsersControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    UsersController usersController;
    @Test
    void testGetAllUserDetails(){
        List<Users> usersList=new ArrayList<>();
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        usersList.add(users);
        when(userService.getAllUsers()).thenReturn(usersList);
        assertEquals(usersList,usersController.getAllUserDetails());
    }
    @Test
    void testGetUserById(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userService.getByUserId(1)).thenReturn(users);
        assertEquals(users,usersController.getUserById(1));

    }
    @Test
    void testGetUserByName(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userService.getByUserName("gopi")).thenReturn(users);
        assertEquals(users,usersController.getUserByName("gopi"));
    }
    @Test
    void testRegisterUsers(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userService.userRegister(users)).thenReturn(users);
        assertEquals(users,usersController.registerUsers(users));
    }
    @Test
    void testUpdateUsers(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");

        when(userService.updateUser(users)).thenReturn(users);
        assertEquals(users,usersController.updateUsers(users));
    }
    @Test
    void testDeleteUsers() {
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");
        when(userService.deleteUser(1)).thenReturn("user deleted successfully");
        String deleteUser = usersController.deleteUsers(1);
        assertEquals("user deleted successfully", deleteUser);

    }
    @Test
    void testUserLogin(){
        Users users=new Users(1,"gopi","gopi@gmail.com","gopi12");

        when(userService.userLogin("gopi","gopi12")).thenReturn("login successfully by given the username and password");
        String userLogin=usersController.userLogin("gopi","gopi12");
        assertEquals("login successfully by given the username and password",userLogin);

    }


}
