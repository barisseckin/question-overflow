package com.developertools.questionoverflow.configuration;

import com.developertools.questionoverflow.model.Category;
import com.developertools.questionoverflow.model.Link;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.repository.CategoryRepository;
import com.developertools.questionoverflow.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(UserRepository userRepository,
                      CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
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

        userRepository.save(user);

        Category category = new Category(
                "java"
        );

        categoryRepository.save(category);
    }

}
