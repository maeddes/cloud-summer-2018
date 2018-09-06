package com.example.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/")
@EnableDiscoveryClient
@EnableFeignClients
public class FrontendApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private DataClient dataClient;

	@GetMapping("/test")
	public String test(){

		return "Ok";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getItems(Model model){

		System.out.println("In getItems: "+model);

		String[] todos = dataClient.listTodos();

		System.out.println("In getItems: "+todos);

		if(todos != null){
			model.addAttribute("items", todos);
		}

		model.addAttribute("name","Summer School");
		return "items";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String addItem(String toDo){

		System.out.println("In addItem: " + toDo);

		String response = dataClient.addTodo(toDo);

		System.out.println("UI.addItem - POST Response: " + response);

		return "redirect:/";

	}

	@RequestMapping(value = "/done/{toDo}", method = RequestMethod.POST)
	public String setItemDone(@PathVariable String toDo){

		System.out.println("In addItem: " + toDo);

		String response = dataClient.removeTodo(toDo);

		System.out.println("UI.addItem - POST Response: " + response);

		return "redirect:/";

	}

	public static void main(String[] args) {
		SpringApplication.run(FrontendApplication.class, args);
	}
}

@FeignClient(name = "data-service")
interface DataClient {

	@GetMapping("/todos")
	String[] listTodos();

	@PostMapping("/todos/{todo}")
	String addTodo(@PathVariable String todo);

	@DeleteMapping("/todos/{todo}")
	String removeTodo(@PathVariable String todo);

}
