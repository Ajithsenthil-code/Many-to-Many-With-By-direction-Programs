package com.example.manytomany.dto;

import java.util.Set;

public class StudentDTO {
	
	    private Long id;
	    private String name;
	    private Set<Long> courseIds;
	    
	    public StudentDTO() {}

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Set<Long> getCourseIds() {
	        return courseIds;
	    }

	    public void setCourseIds(Set<Long> courseIds) {
	        this.courseIds = courseIds;
	    }
}
