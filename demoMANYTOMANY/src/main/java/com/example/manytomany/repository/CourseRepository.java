package com.example.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manytomany.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
