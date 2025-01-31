package com.sec.springsecurity.repository;

import com.sec.springsecurity.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findByWhatsappNumber(String whatsappNumber);
}



