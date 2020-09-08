package com.example.demo;

import com.sun.glass.ui.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootSystemApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("dadsada");
	}

}
