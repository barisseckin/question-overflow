package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.exception.generic.NotFoundException;
import com.developertools.questionoverflow.model.VerificationCode;
import com.developertools.questionoverflow.repository.VerificationCodeRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    public VerificationCodeService(VerificationCodeRepository verificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
    }

    protected VerificationCode save(String userMail, int request) {
        VerificationCode code = new VerificationCode(userMail, request);
        return verificationCodeRepository.save(code);
    }

    protected VerificationCode getByUser(String userMail) {
        return verificationCodeRepository.findVerificationCodeByUserMail(userMail)
                .orElseThrow(() -> new NotFoundException("user not found, mail: " + userMail));
    }

    protected boolean verifyCode(String userMail, int code) {
        VerificationCode verificationCode = getByUser(userMail);

        return verificationCode.getCode() == code;
    }

    protected void deleteByUserMail(String userMail) {
        VerificationCode verificationCode = getByUser(userMail);
        verificationCodeRepository.deleteById(verificationCode.getId());
    }

    protected int generateCode() {
        return new Random().nextInt(99999);
    }
}
