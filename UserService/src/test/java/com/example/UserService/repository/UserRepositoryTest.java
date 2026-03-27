package com.example.UserService.repository;

import com.example.UserService.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles("test")
@EntityScan(basePackageClasses = UserEntity.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUserName_UserExists() {
        UserEntity user = new UserEntity();
        user.setUserName("nav200408");
        user.setEmail("anhvu08032004@gmail.com");
        user.setFullName("Nguyen Anh Vu");
        userRepository.save(user);
        UserEntity foundUser = userRepository.findByUserName("nav200408");
        Assertions.assertNotNull(foundUser, "Should find the user we just saved");
        Assertions.assertEquals("nav200408", foundUser.getUserName());
        Assertions.assertEquals("Nguyen Anh Vu", foundUser.getFullName());
    }

    @Test
    void testFindByUserName_UserDoesNotExist() {
        UserEntity foundUser = userRepository.findByUserName("ghost_user");
        Assertions.assertNull(foundUser, "Searching for a non-existent user should return null");
    }
}
