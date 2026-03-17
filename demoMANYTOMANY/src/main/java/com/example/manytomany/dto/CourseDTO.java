package com.example.manytomany.dto;

import java.util.Set;

public class CourseDTO {
	
	private Long id;
    private String title;
    private Set<Long> studentIds;
    
    public CourseDTO() {}
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public Set<Long> getStudentIds() {
    return studentIds;
}

public void setStudentIds(Set<Long> studentIds) {
    this.studentIds = studentIds;


}
}
