package com.berkayg.repository;

import com.berkayg.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserProfile,String> {
    Optional<UserProfile> findByAuthId(Long authId);
}