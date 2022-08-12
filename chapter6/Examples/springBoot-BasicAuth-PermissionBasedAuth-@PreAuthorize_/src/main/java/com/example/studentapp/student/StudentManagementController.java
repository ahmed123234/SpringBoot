package com.example.studentapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentService studentService;

    @GetMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    // TODO: can take: hasRole('ROLE_'), hasAnyRole('ROLE_'), hasAuthority('permission'),hasAnyAuthority('permission')
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('student:write')")
    public String registerNewStudent(@RequestBody Student student) {
        //       if(studentRepo.save(student).getStudentID()!=null)
       if (studentService.RegisterStudent(student).getStudentID()!= null)
           return "student registered successfully.";
       else
           return "Error registering new student!. Please try again later.";
    }

    @DeleteMapping(path = "{studentId}")
    //@PreAuthorize("hasAuthority('student:write')")
    public String deleteStudent(@PathVariable("studentId") Integer studentId) {

         //return studentService.deleteStudent(studentId);
        //studentRepo.deleteById(studentId);
        return "deleted";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    @GetMapping(path = "{Id}")
    public Student getStudentById(@PathVariable Integer Id){
       return studentService.findByStudentID(Id);
    }

    @PutMapping(path = "{studentId}")
    //@PreAuthorize("hasAuthority('student:write')")
    public Student updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {

       return studentService.updateStudent(studentId,student);
    }

}
