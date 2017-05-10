package io.javabrains.springbootstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.dto.Course;
import io.javabrains.springbootstarter.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(path = "/topics/{id}/courses", method = RequestMethod.GET)
	public List<Course> getAllCourses (@PathVariable String id) {
		return courseService.listAllCourses(id);
	}
	
	@RequestMapping(path = "/topics/{topicId}/courses/{id}", method = RequestMethod.GET)
	public Course getCourse (@PathVariable String id) {
		return courseService.getCourse(id);
	}
	
	@RequestMapping(path = "/topics/{topicId}/courses", method = RequestMethod.POST)
	public void addTopic (@RequestBody Course course, @PathVariable String topicId) {
		courseService.add(course, topicId);
	}

	@RequestMapping(path = "/topics/{topicId}/courses", method = RequestMethod.PUT)
	public void updateTopic (@RequestBody Course course, @PathVariable String topicId) {
		courseService.update(course, topicId);
	}
	
	@RequestMapping(path = "/topics/{topicId}/courses/{id}", method = RequestMethod.DELETE)
	public void deleteTopic (@PathVariable String id) {
		courseService.delete(id);
		
	}
}
