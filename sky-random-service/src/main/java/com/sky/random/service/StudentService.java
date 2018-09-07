package com.sky.random.service;


import com.sky.random.entity.Student;

public interface StudentService {
    Student selectStudentById(Long id);
    void save(Student student);
}
