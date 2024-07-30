package com.example.school.repository;

import java.util.ArrayList;
import com.example.school.model.Student;

public interface StudentRepository {
     ArrayList<Student> getAllStudents();

     Student getStudentById(int studentId);

     Student addStudent(Student student);

     String addMultipleStudents(ArrayList<Student> studentList);

     Student updateStudent(int studentId, Student student);

     void deleteStudent(int studentId);
}