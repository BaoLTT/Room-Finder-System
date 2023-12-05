package com.roomfindingsystem.repository;


import com.roomfindingsystem.entity.UserEntity;
import com.roomfindingsystem.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

   @Autowired
   private  UserRepository userRepository;


   @Test
   void testFindByEmailWhenEmailExists() {
      // Arrange
      String emailToFind = "baoltthe153367@fpt.edu.vn";
      UserEntity userEntity = new UserEntity();
//      userEntity.setEmail(emailToFind);
      userEntity.setUserId(4);

      // Act
      Optional<UserEntity> result = userRepository.findByEmail(emailToFind);

      System.out.println(result);

      // Assert
      assertTrue(result.isPresent());
//      assertEquals(emailToFind, result.get().getEmail());
      assertEquals(4, result.get().getUserId());
      // Add more assertions based on your business logic and expectations
   }


   @Test
   void testFindByEmailWhenEmailDoesNotExist() {
      // Arrange
      String nonExistentEmail = "nonexistent@example.com";

      // Act
      Optional<UserEntity> result = userRepository.findByEmail(nonExistentEmail);

      // Assert
      assertFalse(result.isPresent());
   }

   @Test
   void testFindByEmail_EmailIsNull() {
      // Test Case 3: Email có giá trị là null


      // Gọi phương thức cần kiểm thử từ service
      Optional<UserEntity> result = userRepository.findByEmail(null);

      // Kiểm tra kết quả
      assertFalse(result.isPresent());


   }

   @Test
   void testFindByEmail_EmailIsEmpty() {
      // Test Case 4: Email có giá trị trống

      // Gọi phương thức cần kiểm thử từ service
      Optional<UserEntity> result = userRepository.findByEmail("");

      // Kiểm tra kết quả
      assertFalse(result.isPresent());


   }

   @Test
   void testFindByEmail_EmailExceedsMaxLength() {
      // Test Case 5: Email có độ dài vượt quá giới hạn
      String longEmail = "a".repeat(256); // Giả sử giới hạn là 255 ký tự


      // Gọi phương thức cần kiểm thử từ service
      Optional<UserEntity> result = userRepository.findByEmail(longEmail);

      // Kiểm tra kết quả
      assertFalse(result.isPresent());


   }

   @Test
   void testFindByEmail_EmailCaseInsensitive() {
      // Test Case 7: Email tồn tại nhưng với ký tự hoa/thường khác nhau
      String originalEmail = "baoltthe153367@fpt.edu.vn";
      UserEntity userEntity = new UserEntity();
      userEntity.setEmail(originalEmail);


      String uppercaseEmail = "BAOLTTHE153367@FPT.EDU.VN";

      Optional<UserEntity> result = userRepository.findByEmail(uppercaseEmail);

      assertTrue(result.isPresent());
      assertEquals(originalEmail, result.get().getEmail());

   }

}
