package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findVerificationCodeByUserMail(String mail);
}
