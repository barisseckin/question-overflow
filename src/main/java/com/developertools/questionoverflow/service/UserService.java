package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.UserDto;
import com.developertools.questionoverflow.dto.converter.UserDtoConverter;
import com.developertools.questionoverflow.dto.request.CreateUserRequest;
import com.developertools.questionoverflow.dto.request.SendMailRequest;
import com.developertools.questionoverflow.model.Link;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final MailService mailService;
    private final VerificationCodeService verificationCodeService;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter,
                       MailService mailService, VerificationCodeService verificationCodeService) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.mailService = mailService;
        this.verificationCodeService = verificationCodeService;
    }

    public UserDto save(CreateUserRequest request) {
        List<Link> links = new ArrayList<>();

        for (String link : request.getLinks()) {
            links.add(new Link(link));
        }

        User savedUser = new User(
                request.getUsername(),
                request.getMail(),
                request.getPassword(),
                request.getUrlToImage(),
                links                           //TODO: bugfix
        );

        return userDtoConverter.convertUserToUserDto(userRepository.save(savedUser));
    }

    public void sendActivateCode(String mail) {
        int verificationCode = verificationCodeService.generateCode();

        verificationCodeService.save(mail, verificationCode);

        mailService.send(new SendMailRequest("Your Verification Code", String.valueOf(verificationCode), mail));
    }

    public UserDto activateUser(String userMail, int code) {
        User user = getByMail(userMail);

        if (verificationCodeService.verifyCode(user.getMail(), code)) {
            user.setActive(true);
            verificationCodeService.deleteByUserMail(user.getMail());
        }

        return userDtoConverter.convertUserToUserDto(userRepository.save(user));
    }

    public void deleteByMail(String mail) {
        User user = getByMail(mail);
        userRepository.deleteById(user.getId());
    }

    public UserDto getByUserMail(String mail) {
        return userDtoConverter.convertUserToUserDto(getByMail(mail));
    }

    protected User getByMail(String mail) {
        return userRepository.findUserByMail(mail)
                .orElseThrow(() -> new RuntimeException(""));
    }
}
