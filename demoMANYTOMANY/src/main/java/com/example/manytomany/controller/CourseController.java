package com.example.manytomany.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.example.manytomany.dto.CourseDTO;
import com.example.manytomany.entity.Course;
import com.example.manytomany.entity.Student;
import com.example.manytomany.repository.CourseRepository;
import com.example.manytomany.service.CourseService;
import com.example.manytomany.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

	 @Autowired
	    private CourseService courseService;

	    @Autowired
	    private StudentService studentService;
	    
	    @Autowired
	    private CourseRepository repository;
	    
	    @PostMapping
	    public CourseDTO create(@RequestBody @Valid Course c) {
	        return courseService.getById(courseService.create(c).getId());
	    }
	    
	    @GetMapping
	    public List<CourseDTO> getAll() {
	        return courseService.getAll()
	        		.stream()
	                .map(c -> courseService.getById(c.getId()))
	                .collect(Collectors.toList());
	    }
	    @GetMapping("/{id}")
	    public CourseDTO getById(@PathVariable Long id) {
	        return courseService.getById(id);
	    }
	    @PutMapping("/{id}")
	    public CourseDTO update(@PathVariable Long id, @RequestBody @Valid Course c) {
	        return courseService.update(id, c);
	    }
	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        courseService.delete(id);
	        return "Course with ID " + id + " deleted successfully.";
	    }
	    @GetMapping("/{id}/students")
	    public Set<Student> getStudents(@PathVariable Long id) {
	        return repository.findById(id).orElseThrow().getStudents();
	    }
	}



