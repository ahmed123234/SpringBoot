package com.example.studentapp.student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Ahmad Ghannam"),
            new Student(2, "Omar Malli"),
            new Student(3, "Hassan Jaber")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        return STUDENTS;
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        STUDENTS.add(student);
        System.out.println(student);
    }
    @DeleteMapping(path = "{studentId}")
    public Student deleteStudent(@PathVariable("studentId") Integer studentId) {
        Student stud = STUDENTS.stream().filter(student -> studentId.equals(student.getStudentID()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student " + studentId + " does not exists"));

//        int index = Arrays.asList(STUDENTS).indexOf(stud.getStudentName());
//        return STUDENTS.remove(index);
//            System.out.println("Student " + stud.getStudentName() + " is deleted successfully.");
//        else
//            System.out.println("Student did not removed!. Please try again later");
        return stud;
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {

        System.out.println(String.format("%s %s", studentId, student));
    }


}
