
package com.example.school.controller;

import com.example.school.service;
import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController

public class StudentController {
    @Autowired
    private StudentH2Service studentservice;

    @GettMapping("/students")

    public ArrayList<Student> getAllStudents() {
        return studentservice.getAllStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentservice.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return stuentservice.addStudent(student);

    }
    @PostMapping("/students/bulk")
    public String addMultipleStudents(@RequestBody ArrayList<Student>studentList){
        return studentservice.addMultipleStudents(studentList);
    }

    @PutMapping
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return studentservice.updateStudent(studentId, student);
    }

    @DeleteMapping
    public void DeleteStudent(@pathVariable("studentId") int studentId) {
        studentservice.deleteStudent(studentId);
    }

}
