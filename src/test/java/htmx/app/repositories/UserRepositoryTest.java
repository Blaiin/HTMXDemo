package htmx.app.repositories;

import htmx.app.utils.Utils;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Utils utils;

	@Test
	public void existsByEmailAndPassword() {
		String email = "dandyser@hotmail.it";
		String password = "pass";
		boolean expected = true;
		boolean actual = userRepository.existsByEmailAndPassword(email, utils.encrypt(password));

		assertEquals(expected, actual);
	}
}
