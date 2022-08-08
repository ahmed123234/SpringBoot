package com.example.springbootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MyController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/insert")
    public String index(){
        jdbcTemplate.execute("insert into users(name,email) values ('Ahmad', 'ahmad@gmail.com')");
        return "data inserted successfully";
    }
    @RequestMapping(value = "/add")
    public String index(@RequestParam(value = "name") String userName, @RequestParam(value = "email") String email){
        String query ="insert into users(name,email) values (?, ?)";
        jdbcTemplate.update(query,userName,email);
        return "data inserted successfully";
    }

    @RequestMapping("/update_email")
    public String update(@RequestParam("id") int id, @RequestParam(value = "email") String email){
        String query ="update users set email = ? where id = ?";
        jdbcTemplate.update(query,email,id);
        return "data updated successfully";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id){
        String query ="delete from users where id = ?";
        jdbcTemplate.update(query, id);
        return "data deleted successfully";
    }

    @RequestMapping("/fetchAll")
    public List<Map<String, Object>> fetchAll(){

        return jdbcTemplate.queryForList("select * from users");

    }

    @RequestMapping("/retrieve")
    public List<Map<String, Object>> retrieve(@RequestParam("id") int id){
        return jdbcTemplate.queryForList("select * from users where id = ?",id);

    }
}
