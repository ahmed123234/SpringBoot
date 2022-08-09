package com.example.springbootjpaexample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Person, Integer> {
    public Person findByEmail(String email);
    public  Person findByIdAndEmail(int id, String email);
    public List<Person> findAllByName(String name);

    public List<Person> findAll();

}
