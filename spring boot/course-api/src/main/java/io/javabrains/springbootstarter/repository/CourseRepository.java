package io.javabrains.springbootstarter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.javabrains.springbootstarter.dto.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
	
	public List<Course> getCoursesByTopicId (String topicId);

}
