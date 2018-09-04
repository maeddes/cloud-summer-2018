package com.example.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class FrontendApplication {

	@GetMapping("/test")
	public String test(){

		return "Ok";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getItems(Model model){

		System.out.println("In getItems: "+model);

		RestTemplate template = new RestTemplate();

		String url = "http://localhost:8080/todos/";

		ResponseEntity<String[]> response = template.getForEntity(url, String[].class);

		System.out.println("In getItems: "+response);

		if(response != null){
			model.addAttribute("items", response.getBody());
		}

		model.addAttribute("name","HSE1801");
		return "items";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String addItem(String toDo){

		System.out.println("In addItem: " + toDo);

		RestTemplate template = new RestTemplate();

		String url = "http://localhost:8080/todos/"+toDo;

		ResponseEntity<String> response = template.postForEntity(url, null, String.class);
		System.out.println("UI.addItem - POST Response: " + response.getBody());

		return "redirect:/";

	}

	@RequestMapping(value = "/done/{toDo}", method = RequestMethod.POST)
	public String setItemDone(@PathVariable String toDo){

		System.out.println("In addItem: " + toDo);

		RestTemplate template = new RestTemplate();

		String url = "http://localhost:8080/todos/" + toDo;

		template.delete(url);

		return "redirect:/";

	}

	public static void main(String[] args) {
		SpringApplication.run(FrontendApplication.class, args);
	}
}
