package com.example.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class DataApplication {

	@Autowired
	TodoRepository todoRepository;

	@GetMapping("/todos")
	public ArrayList<String> getAll(){

		ArrayList<String> todos = new ArrayList<String>();
		List<Todo> arrayTodo = (List<Todo>) todoRepository.findAll();

		for (int i = 0; i < arrayTodo.size(); i++) {

			todos.add(arrayTodo.get(i).toString());

		}

		return todos;
	}

	@PostMapping("/todos/{todo}")
	public String add(@PathVariable String todo){

		todoRepository.save(new Todo(todo));
		return "added "+todo;
	}

	@DeleteMapping("/todos/{todo}")
	public String remove(@PathVariable String todo){

		todoRepository.delete(new Todo(todo));
		return "remove "+todo;
	}

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}
}

@Entity
class Todo{

	@Id
	String todo;

	public Todo(){}

	public Todo(String todo){

		this.todo = todo;
	}

	public String toString(){

		return todo;
	}

}

interface TodoRepository extends CrudRepository<Todo,String>{}