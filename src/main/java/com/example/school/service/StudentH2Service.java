
package com.example.school.servive;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@service
public class StudentH2Service implements Studentrepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    ArrayList<Student> getAllStudents() {
        List<Student> studentData = db.query("select * from student", new studentRowMapper());
        ArrayList<Student> studentList = new ArrayList<>(studentData);
        return studentList;
    }

    @Override
    public Student getStudentById(int studentId) {
        try{
            return db.queryForObject("select * from student where studentId=?", new studentRowMapper(),
                student.getStudentId());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
   public  Student addStudent(Student student) {
        db.update("insert into student(studentName,gender,standard) values(?,?,?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        return db.queryForObject("select * from student where studentName=? and gender=?", new studentRowMapper(),
                student.getStudentName(), student.getGender());
    }

    @Override
    public String addMultpleStudents(ArrayList<Student> studentList) {
        for (Student eachStudent : studentList) {
            db.update("insert into student(studentName,gender,standard) values(?,?,?)", eachStudent.getStudentName(),
                    eachStudent.getGender(), eachStudent.getStandard());
        }
        String responseMessage = String.format("Successfully added %d students", studentList.size());
        return responseMessage;
    }

    @Override
    void deleteStudent(int studentId) {
        return db.update("delete from student where studentId=?", studentId);
    }

    @Override
    Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update student set studentName=? where studentId=?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update student set gender=? where studentId=?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update student set standard=? where studentId=?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }
}
