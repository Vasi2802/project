package org.antwalk.controller;

import java.util.List;
import java.util.ArrayList;

import org.antwalk.entity.Bus;
import org.antwalk.entity.Stop;
import org.antwalk.entity.Employee;
import org.antwalk.repository.BusRepo;
import org.antwalk.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepo empRepo;
	
	@Autowired
	BusRepo busRepo;

	@PostMapping("/insert")
	public Employee insert(@RequestBody Employee e) {
		return empRepo.save(e);
	}

	@GetMapping("/getall")
	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public Employee getById(@PathVariable long id) {
		return empRepo.findById(id).get();
	}

	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable long id) {
		empRepo.deleteById(id);
		return "Deleted";
	}

	@PutMapping("/update/{id}")
	public String update(@RequestBody Employee e, @PathVariable long id) {
		List<Employee> empList = empRepo.findAll();
		for (Employee obj : empList) {
			if (obj.getEid() == id) {
				if (e.getEid() == id) {
					empRepo.save(e);
					return "Updated";
				}

				else {
					return "Id doesn't match";
				}

			}
		}
		return "Id does not exist";

	}

	@GetMapping("/")
	public String hello(){
		return "Hello";
	}

	@GetMapping("/login")
	public ModelAndView employeeLogin() {
		ModelAndView modelAndView = new ModelAndView("employeeLoginPage");
		return modelAndView;
	}

	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView modelAndView = new ModelAndView("employeeDashboard");
		modelAndView.addObject("employee", new Employee(20, "Name1", "10000000000", "aa@aa.com", "aaaaa", null));
		return modelAndView;
	}

	@GetMapping("/book")
	public ModelAndView book() {
		ModelAndView modelAndView = new ModelAndView("employeeBook");
		modelAndView.addObject("employee", new Employee(20, "Name1", "10000000000", "aa@aa.com", "aaaaa", null));
		List<Stop> stops = new ArrayList(); // temporary list of stops. to be fetched
		stops.add(new Stop(1, "Stop1"));
		stops.add(new Stop(2, "Stop2"));
		stops.add(new Stop(3, "Stop3"));
		stops.add(new Stop(4, "Stop4"));
		stops.add(new Stop(5, "Stop5"));

		modelAndView.addObject("stops", stops);
		return modelAndView;
	}

	@GetMapping("/edit")
	public ModelAndView employeeEditForm() {
		ModelAndView modelAndView = new ModelAndView("employeeEdit");
		return modelAndView;
	}

	@PostMapping("/editemployeedetails")
	public Employee editEmployee(@RequestBody Employee employee) {
		return employee;
	}

}
