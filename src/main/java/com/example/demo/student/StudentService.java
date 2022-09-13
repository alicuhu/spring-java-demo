package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> foundStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (foundStudent.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("student with id " + id + " does not exist"));
        if (name != null && !name.isEmpty() && !name.equals(student.getName())) {
            student.setName(name);
        }
        if (email != null && !email.isEmpty() && !email.equals(student.getName())) {
            Optional<Student> foundStudent = studentRepository.findStudentByEmail(email);
            if (foundStudent.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
        studentRepository.save(student);
    }
}
