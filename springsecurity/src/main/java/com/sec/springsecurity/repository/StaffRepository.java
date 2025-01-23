package com.sec.springsecurity.repository;

import com.sec.springsecurity.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String> {
}
