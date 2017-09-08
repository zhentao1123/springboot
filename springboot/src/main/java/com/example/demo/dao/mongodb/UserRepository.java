package com.example.demo.dao.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.User;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByName(String username);
    
    User findById(Long id);

}
