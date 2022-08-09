package com.example.springbootjpaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("users/create")
    public String createUser(@RequestParam("id") int id,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email){

        Person person = new Person(id, name, email);
        userRepository.save(person);
        return "User successfully created";
    }

    @RequestMapping("users/delete")
    public String deleteById(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return "User deleted Successfully";
    }

    @RequestMapping("users/deleteAll")
    public String deleteAll() {
        userRepository.deleteAll();
        return "Users deleted Successfully";
    }

    @RequestMapping("users")
    public List<Person> getAllUsers() {

        return userRepository.findAll();
    }

    @RequestMapping("users/fetchAll")
    public List<Person> getUsersByName(@RequestParam("name") String name) {

        return userRepository.findAllByName(name);
    }


    @RequestMapping("users/get-by-email")
    public Person getUsersByEmail(@RequestParam("email") String email) {

        return userRepository.findByEmail(email);
    }


    @RequestMapping("users/get-by-id&email")
    public Person getUsersByIdAndEmail (@RequestParam("id") int id,
                                      @RequestParam("email") String email) {
        return userRepository.findByIdAndEmail(id, email);
    }
}

