package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.UserDto;
import com.developertools.questionoverflow.dto.converter.UserDtoConverter;
import com.developertools.questionoverflow.dto.request.CreateUserRequest;
import com.developertools.questionoverflow.dto.request.SendMailRequest;
import com.developertools.questionoverflow.exception.generic.NotFoundException;
import com.developertools.questionoverflow.exception.user.UserExistException;
import com.developertools.questionoverflow.exception.user.UserNotActiveException;
import com.developertools.questionoverflow.model.LikedQuestion;
import com.developertools.questionoverflow.model.Link;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.repository.UserRepository;
import com.developertools.questionoverflow.utils.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final MailService mailService;
    private final VerificationCodeService verificationCodeService;
    private final LogService logService;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter,
                       MailService mailService,
                       VerificationCodeService verificationCodeService,
                       LogService logService) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.mailService = mailService;
        this.verificationCodeService = verificationCodeService;
        this.logService = logService;
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
                links
        );

        if (!userRepository.existsByMail(savedUser.getMail())) {
            return userDtoConverter.convertUserToUserDto(userRepository.save(savedUser));
        }

        throw new UserExistException("user exist, mail: " + savedUser.getMail());
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
            user.setUpdateDate(LocalDateTime.now());
            mailService.send(new SendMailRequest("Your Account Verified",
                    "Hello, this e-mail has been sent automatically to " +
                            "indicate that your account has been verified."
                    ,user.getMail()));
            verificationCodeService.deleteByUserMail(user.getMail());
        }

        return userDtoConverter.convertUserToUserDto(userRepository.save(user));
    }

    public void deleteByMail(String mail) {
        User user = getByMail(mail);
        userRepository.deleteById(user.getId());
        mailService.send(new SendMailRequest("Your Account Deleted",
                "We are sorry that your account " +
                        "has been deleted. hope to see you again",
                user.getMail()));

        logService.save(mail + " user deleted");
    }

    public UserDto getByUserMail(String mail) {
        return userDtoConverter.convertUserToUserDto(getByMail(mail));
    }

    public UserDto updateNotificationPermissionByMail(String mail, boolean permission) {
        User fromDbUser = getByMail(mail);

        if (fromDbUser.isActive()) {
            fromDbUser.setNotificationPermission(permission);
            fromDbUser.setUpdateDate(LocalDateTime.now());
            return userDtoConverter.convertUserToUserDto(userRepository.save(fromDbUser));
        }

        throw new UserNotActiveException("user not active, mail: " + mail);
    }

    public void updateTotalReportNumberByMail(String mail) {
        User fromDbUser = getByMail(mail);
        fromDbUser.setTotalReportNumber(fromDbUser.getTotalReportNumber() + 1);

        if (fromDbUser.getTotalReportNumber() >= 100) {
            fromDbUser.setActive(false);

            if (fromDbUser.isNotificationPermission()) {
                mailService.send(new SendMailRequest("Your Account Deactivated!",
                        "Your account has been deactivate for review due to too many reports for your content!",
                        fromDbUser.getMail()));
            }

            logService.save(mail + " user deactivated");
        }

        userRepository.save(fromDbUser);
    }

    public void addPublicIdOfLikedQuestions(String publicId, String mail) {
        User fromDbUser = getByMail(mail);
        List<LikedQuestion> fromDbUserLikedQuestion = fromDbUser.getPublicIdOfLikedQuestions();
        fromDbUserLikedQuestion.add(new LikedQuestion(publicId));

        fromDbUser.setPublicIdOfLikedQuestions(fromDbUserLikedQuestion);

        userRepository.save(fromDbUser);
    }

    protected User getByMail(String mail) {
        return userRepository.findUserByMail(mail)
                .orElseThrow(() -> new NotFoundException("user not found, mail: " + mail));
    }
}
