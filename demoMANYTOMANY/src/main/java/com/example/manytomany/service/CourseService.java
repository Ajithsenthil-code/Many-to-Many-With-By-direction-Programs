package com.example.manytomany.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.ResourceAccessException;

import com.example.manytomany.dto.CourseDTO;
import com.example.manytomany.dto.StudentDTO;
import com.example.manytomany.entity.Course;
import com.example.manytomany.exception.ResourceNotFoundException;
import com.example.manytomany.mapper.StudentMapper;
import com.example.manytomany.repository.CourseRepository;
import com.example.manytomany.repository.StudentRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repo;
	
	@Autowired
	private StudentRepository studentrepo;
	
	@Autowired
	private StudentMapper mapper;
	
//	@Autowired
//	private StudentMapper mapper;
	
	
	public Course create(Course c) {
		return repo.save(c);
	}
	public List<Course> getAll(){
		return repo.findAll();
	}
	public CourseDTO getById(Long id) {
		Course c=repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		
		CourseDTO dto=new CourseDTO();
		dto.setId(c.getId());
		dto.setTitle(c.getTitle());
		dto.setStudentIds(
				c.getStudents()
				.stream()
				.map(student ->student.getId())
				.collect(Collectors.toSet())
				);
		
		return dto;
		
	}
	
	public CourseDTO update(Long id,Course c) {
		
		Course excisting= repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		
		
		excisting.setTitle(c.getTitle());
		excisting.setStudents(c.getStudents());
		
		Course updated = repo.save(excisting);
		
		
		CourseDTO dto=new CourseDTO();
		dto.setId(updated.getId());
		dto.setTitle(updated.getTitle());
		dto.setStudentIds(
		        updated.getStudents()
		               .stream()
		               .map(student -> student.getId())
		               .collect(Collectors.toSet())
		);

		return dto;
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	public Set<StudentDTO> getStudentsOfCourse(Long courseId) {
	    Course course = repo.findById(courseId)
	            .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

	    return course.getStudents()
	                 .stream()
	                 .map(student -> mapper.toDTO(student))
	                 .collect(Collectors.toSet());
	}

}
