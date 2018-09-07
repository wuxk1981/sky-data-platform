package com.sky.random.repository;

import com.sky.random.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by luhaoyuan on 2017/2/9.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {


}
