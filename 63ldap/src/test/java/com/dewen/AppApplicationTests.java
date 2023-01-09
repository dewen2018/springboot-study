package com.dewen;

import com.dewen.entity.Person;
import com.dewen.mapper.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void findAll() {

		personRepository.findAll().forEach(p -> {
			System.out.println(p);
		});

	}

	@Test
	public void save() {
		Person person = new Person();
		person.setUid("uid:1");
		person.setUserName("AAA");
		person.setCommonName("aaa");
		person.setUserPassword("123456");
		personRepository.save(person);

		personRepository.findAll().forEach(p -> {
			System.out.println(p);
		});
	}
}
