package com.example.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manytomany.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
