package com.example.studentapp.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends MongoRepository<Student, Integer> {

    public List<Student> findAll();
    public Student findByStudentID(Integer studentId);

    Student findByStudentName(String studentName);
}
