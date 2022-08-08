package com.example.databasejdbc;

import com.example.databasejdbc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate template;

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getAllUsers(){
        //template.queryForList("select * from stock");
        List<User> users = template.query("select * from user",new BeanPropertyRowMapper<>(User.class));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
