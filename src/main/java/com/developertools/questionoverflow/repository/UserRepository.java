package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByMail(String mail);
    boolean existsByMail(String mail);
}
