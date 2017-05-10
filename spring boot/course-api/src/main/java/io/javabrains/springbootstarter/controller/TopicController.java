package io.javabrains.springbootstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.dto.Topic;
import io.javabrains.springbootstarter.service.TopicService;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping(path = "/topics", method = RequestMethod.GET)
	public List<Topic> getAllTopics () {
		return topicService.listAllTopics();
	}
	
	@RequestMapping(path = "/topics/{id}", method = RequestMethod.GET)
	public Topic getTopic (@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(path = "/topics", method = RequestMethod.POST)
	public void addTopic (@RequestBody Topic topic) {
		topicService.add(topic);
	}

	@RequestMapping(path = "/topics/{id}", method = RequestMethod.PUT)
	public void updateTopic (@RequestBody Topic topic) {
		topicService.update(topic);
	}
	
	@RequestMapping(path = "/topics/{id}", method = RequestMethod.DELETE)
	public void deleteTopic (@PathVariable String id) {
		topicService.delete(id);
		
	}
}
