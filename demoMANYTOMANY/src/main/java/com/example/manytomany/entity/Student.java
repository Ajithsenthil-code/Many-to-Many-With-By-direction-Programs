package com.example.manytomany.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank
	private String name;
	
	
	@ManyToMany
	@JoinTable(
			name="student_courses",
			joinColumns = @JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
			)
	@JsonManagedReference
	private Set<Course> courses=new HashSet<>();
	
	public Student() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public Set<Course> getCourses(){
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses=courses;
	}
	

}
