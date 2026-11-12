package com.example.springaop;

import com.example.springaop.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class SpringAopLabApplicationTests {

	@Autowired
	private StudentService studentService;

	@Test
	void contextLoads() {
	}

	@Test
	void aopShouldLogServiceMethod(CapturedOutput output) {

		studentService.getAllStudents();

		assertTrue(output.getOut().contains("Starting method: getAllStudents"));
		assertTrue(output.getOut().contains("Finished method: getAllStudents"));
		assertTrue(output.getOut().contains("Execution time:"));
	}
}