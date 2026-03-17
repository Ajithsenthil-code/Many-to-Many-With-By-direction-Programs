package com.example.manytomany.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Course {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank
	private String title;
	
	@ManyToMany(mappedBy = "courses")
	@JsonBackReference
	private Set<Student> students= new HashSet<>();
	
	
	public Course() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public Set<Student> getStudents(){
		return students;
	}
	public void setStudents (Set<Student> students){
		this.students=students;
	}

}
