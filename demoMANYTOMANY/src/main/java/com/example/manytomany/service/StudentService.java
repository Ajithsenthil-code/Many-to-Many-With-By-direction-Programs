package com.example.manytomany.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.manytomany.dto.StudentDTO;
import com.example.manytomany.entity.Course;
import com.example.manytomany.entity.Student;
import com.example.manytomany.exception.ResourceNotFoundException;
import com.example.manytomany.mapper.StudentMapper;
//import com.example.manytomany.mapper.StudentMapper;
import com.example.manytomany.repository.CourseRepository;
import com.example.manytomany.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	public StudentMapper mapper;
	
	public Student create(Student s) {
		return repo.save(s);
	}
	
	public List<Student> getAll(){
		return repo.findAll();
	}
	
	public StudentDTO getByid(Long id) {
		Student student=repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student id not found"));
		
		return mapper.toDTO(student);
	}
	public StudentDTO update(Student s,Long id) {
		Student excisting = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		//Update fields
		excisting.setName(s.getName());
		//If courses update needed
		excisting.setCourses(s.getCourses());
		
		return mapper.toDTO(repo.save(excisting));
	}
	
	public void delete(Long id) {
		Student excisting=repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		repo.delete(excisting);
	}
	
	public Student addCourse(Long studentId,Long courseId) {
		
		Student s =repo.findById(studentId).orElseThrow();
		Course c=courseRepo.findById(courseId).orElseThrow();
		
		s.getCourses().add(c);
		
		return repo.save(s);
	}
	public Set<Course> getCoursesOfStudent(Long id){
		return repo.findById(id).orElseThrow().getCourses();
	}
	
	

}
