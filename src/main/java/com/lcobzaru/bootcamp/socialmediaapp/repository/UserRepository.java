package com.lcobzaru.bootcamp.socialmediaapp.repository;

import com.lcobzaru.bootcamp.socialmediaapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findByUsername(String username);

}
