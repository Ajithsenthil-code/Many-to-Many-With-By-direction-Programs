package com.example.manytomany.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.manytomany.dto.StudentDTO;
import com.example.manytomany.entity.Course;
import com.example.manytomany.entity.Student;

@Component
public class StudentMapper {
	
	public StudentDTO toDTO(Student s) {
		
		StudentDTO dto= new StudentDTO();
		dto.setId(s.getId());
		dto.setName(s.getName());
		dto.setCourseIds(s.getCourses()
				.stream()
				.map(Course::getId)     //Stream<Long>
				.collect(Collectors.toSet()));
		
		return dto;
	}

}
