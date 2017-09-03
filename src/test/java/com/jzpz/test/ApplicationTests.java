package com.jzpz.test;

import com.jzpz.Application;
import com.jzpz.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private UsersRepository userRepository;
	@Before
	public void setUp() {
	}

	@Test
	public void test() throws Exception {
		System.out.println(userRepository.findAll());

	}


}
