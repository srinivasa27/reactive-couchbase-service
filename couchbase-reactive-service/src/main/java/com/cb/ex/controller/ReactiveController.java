package com.cb.ex.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cb.ex.entity.Employee;
import com.cb.ex.repo.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("http://localhost:4200")
public class ReactiveController {

	@Autowired
	private EmployeeRepository repo;
	
	@Value("${data.stub.enabled}")
	private boolean stubEnabled;
	
	private List<Employee> employeesList = Stream.of(
			new Employee(0, "Rajesh", "Shetty", "Software Engineering"),
			new Employee(1, "Ruchita", "Desu", "Software Engineering"),
			new Employee(2, "John", "Maclain", "Management"),
			new Employee(3, "Sam", "Tarlley", "Finance"),
			new Employee(4, "John", "Snow", "Software Engineering"),
			new Employee(5, "Cerse", "Lanister", "Management"),
			new Employee(6, "Aegon", "Targarian", "Finanace"),
			new Employee(7, "Tyrian", "Lanister", "Management")
		).collect(Collectors.toList());
	
	@GetMapping(value = "/allStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Employee> getAllEmploeesStream(){
		if(stubEnabled) return Flux.fromIterable(employeesList);
		else  return repo.findAll();
	}
	
	@GetMapping(value = "/all")
	public Flux<Employee> getAllEmployees(){
		if(stubEnabled) return Flux.fromIterable(employeesList);
		else  return repo.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public Mono<Employee> getEmployee(@PathVariable int id){
		if(stubEnabled) return Mono.just(employeesList.stream().filter(e->e.getId() == id).findFirst().get());
		else  return repo.findById(id);
		
	}
	
	@PostMapping("/init")
	public Flux<Employee> initateLoadAll(){
		return repo.saveAll(Stream.of(
				new Employee(0, "Rajesh", "Shetty", "Software Engineering"),
				new Employee(1, "Ruchita", "Desu", "Software Engineering"),
				new Employee(2, "John", "Maclain", "Management"),
				new Employee(3, "Sam", "Tarlley", "Finance"),
				new Employee(4, "John", "Snow", "Software Engineering"),
				new Employee(5, "Cerse", "Lanister", "Management"),
				new Employee(6, "Aegon", "Targarian", "Finanace"),
				new Employee(7, "Tyrian", "Lanister", "Management")
			).collect(Collectors.toList()));
	}
	
	@PostMapping("/addEmployee")
	public Mono<Employee> addEmployee(@RequestBody Employee employee){
		if(stubEnabled) {
			employeesList.add(employee);
			return Mono.just(employee);
		}
		else return repo.save(employee);
	}
}
