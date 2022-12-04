package com.developertools.questionoverflow.configuration;

import com.developertools.questionoverflow.model.Category;
import com.developertools.questionoverflow.model.Link;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.repository.CategoryRepository;
import com.developertools.questionoverflow.repository.UserRepository;
import com.developertools.questionoverflow.utils.log.service.LogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LogService logService;

    public DataLoader(UserRepository userRepository,
                      CategoryRepository categoryRepository,
                      LogService logService) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.logService = logService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User(
                "barisseckin",
                "barisseckin0@gmail.com",
                "12345",
                "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                Arrays.asList(new Link("https://github.com/barisseckin"),
                        new Link("https://www.linkedin.com/in/barisseckin/")));

        user.setActive(true);

        userRepository.save(user);
        logService.save(user + " user auto generated (test user)");

        Category category = new Category(
                "java"
        );

        categoryRepository.save(category);
    }

}
