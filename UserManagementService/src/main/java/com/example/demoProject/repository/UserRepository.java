package com.example.demoProject.repository;


import com.example.demoProject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    public Users findByUserId(int id);
    public Users findByUserName(String name);
    public  Users findByPassword(String password);

}
