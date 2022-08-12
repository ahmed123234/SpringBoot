package com.example.studentapp.student;

import org.springframework.data.annotation.Id;

//@Entity
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentID;
    private String studentName;

    public Student(Integer studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }


}
