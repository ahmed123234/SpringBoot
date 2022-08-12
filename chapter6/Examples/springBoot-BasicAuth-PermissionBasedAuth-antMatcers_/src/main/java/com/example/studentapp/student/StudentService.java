package com.example.studentapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student RegisterStudent(Student student){

        return studentRepo.save(student);

    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

//    public Student getStudentById(Integer studentId){
//        return studentRepo.findByStudentID(studentId);
//    }
//
//    public Student getStudentByName(String studentName){
//        return studentRepo.findByStudentName(studentName);
//    }
    public String deleteStudent(Integer studentId ){
        studentRepo.deleteById(studentId);
        return "product successfully removed!";
    }

    public  Student updateStudent(Integer studentId, Student student){
        Student existingStudent = studentRepo.findByStudentID(studentId);
        Student updatedStudent = existingStudent;

                updatedStudent.setStudentName(student.getStudentName());
        studentRepo.delete(existingStudent);

        return studentRepo.save(updatedStudent);
    }

    public Student findByStudentID(Integer id) {
        return studentRepo.findByStudentID(id);
    }
}
