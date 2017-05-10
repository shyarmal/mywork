package io.javabrains.springbootstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.dto.Course;
import io.javabrains.springbootstarter.dto.Topic;
import io.javabrains.springbootstarter.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> listAllCourses (String topicId) {
		return courseRepository.getCoursesByTopicId(topicId);
	}

	public Course getCourse (String id) {
		return courseRepository.findOne(id);
	}

	public void add(Course course, String topicId) {
		course.setTopic(new Topic(topicId, null, null));
		courseRepository.save(course);
	}
	
	public void update (Course course, String topicId) {
		course.setTopic(new Topic(topicId, null, null));
		courseRepository.save(course);
	}
	
	public void delete (String id) {
		courseRepository.delete(id);
	}
}
