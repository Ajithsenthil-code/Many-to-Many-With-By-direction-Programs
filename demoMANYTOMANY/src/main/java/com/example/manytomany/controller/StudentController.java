package com.example.manytomany.controller;

import java.util.List;
import java.util.Set;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.manytomany.dto.StudentDTO;
import com.example.manytomany.entity.Course;
import com.example.manytomany.entity.Student;
import com.example.manytomany.mapper.StudentMapper;
import com.example.manytomany.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	
	 @Autowired
	 private StudentService studentService;
	    @Autowired
	    private StudentMapper mapper;

	    @PostMapping
	    public Student create(@RequestBody @Valid Student s) {
	        return studentService.create(s);
	    }

	    @GetMapping
	    public List<StudentDTO> getAll() {
	        return studentService.getAll()
	        		.stream().map(mapper::toDTO).collect(Collectors.toList());
	    }
	    @GetMapping("/{id}")
	    public StudentDTO getById(@PathVariable Long id) {
	    	return studentService.getByid(id);
	    }
	    @PutMapping("/{id}")
	    public StudentDTO update(@PathVariable Long id, @RequestBody @Valid Student s) {
	        return studentService.update(s, id);
	    }
	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        studentService.delete(id);
	        return "Student with ID " + id + " deleted successfully.";
	    }

	    
	    @PostMapping("/{studentId}/courses/{courseId}")
	    public Student enroll(@PathVariable Long studentId,
	    		               @PathVariable Long courseId) {
	    	return studentService.addCourse(studentId, courseId);
	    }
	    @GetMapping("/{id}/courses")
	    public Set<Course> getCourses(@PathVariable Long id){
	    	return studentService.getCoursesOfStudent(id);
	    }
	    


}
