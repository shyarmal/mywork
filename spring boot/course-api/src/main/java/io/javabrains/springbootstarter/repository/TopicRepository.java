package io.javabrains.springbootstarter.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import io.javabrains.springbootstarter.dto.Topic;

public interface TopicRepository extends CrudRepository<Topic, String>{

}
