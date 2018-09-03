package com.example.intro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class IntroApplication {

	ArrayList<String> todos = new ArrayList<String>();

	@GetMapping("/todos")
	public ArrayList<String> getAll(){

		return todos;
	}

	@PostMapping("/todos/{todo}")
	public String add(@PathVariable String todo){

		todos.add(todo);
		return "added "+todo;
	}

	@DeleteMapping("/todos/{todo}")
	public String remove(@PathVariable String todo){

		todos.remove(todo);
		return "remove "+todo;
	}

	public static void main(String[] args) {
		SpringApplication.run(IntroApplication.class, args);
	}
}
