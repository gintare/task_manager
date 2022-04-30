package com.gintare.tasksmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TasksmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksmanagerApplication.class, args);
	}

	@GetMapping
	public String starterMessage(){
		return "Get ready! Task Manager coming soon!";
	}

}
