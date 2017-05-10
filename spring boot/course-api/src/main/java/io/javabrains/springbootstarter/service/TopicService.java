package io.javabrains.springbootstarter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.dto.Topic;
import io.javabrains.springbootstarter.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public List<Topic> listAllTopics () {
		List<Topic> list = new ArrayList<>();
		topicRepository.findAll().forEach(list::add);
		return list;
	}

	public Topic getTopic (String id) {
		return topicRepository.findOne(id);
	}

	public void add(Topic topic) {
		topicRepository.save(topic);
	}
	
	public void update (Topic topic) {
		topicRepository.save(topic);
	}
	
	public void delete (String id) {
		topicRepository.delete(id);
	}
}
