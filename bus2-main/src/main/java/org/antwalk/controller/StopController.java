package org.antwalk.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.antwalk.entity.Employee;
import org.antwalk.entity.Stop;
import org.antwalk.repository.StopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/stop")
public class StopController {
	
	@Autowired
	StopRepo stopRepo;
	
	@PostMapping("/insert")
	public Stop insert(@RequestBody Stop s) {
		return stopRepo.save(s);
	}
	
	@GetMapping("/getall")
	public List<Stop> getAll(){
		return stopRepo.findAll();
	}
	
	@GetMapping("/getbyid/{id}")
	public Stop getOne(@PathVariable long id) {
		return stopRepo.findById(id).get();
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable long id) {
		stopRepo.deleteById(id);
		return "Deleted";
		
	}
	
	@PutMapping("/update")
	public Stop update(@RequestBody Stop stop) {
	
		stopRepo.save(stop);
		return stop;
		
	}
	
//	@PutMapping("/update/{name}")
//	public String updateByName(@RequestBody Stop s, @PathVariable String name) {
//		return stopService.updateStopByName(s, name);
//		
//	}

	@GetMapping(value="/stopform")
	public ModelAndView postMethodName() {
		ModelAndView modelAndView = new ModelAndView("stopForm");		
		return modelAndView;
	}
	
	@GetMapping(value="/edit")
	public ModelAndView postMethodName2() {
		ModelAndView modelAndView = new ModelAndView("updateform");		
		return modelAndView;
	}
	
	@PostMapping("/editstopdetails")
	public Stop editStop(@RequestBody Stop s) {
		return s;
	}
	
	@GetMapping("/managing")
	public ModelAndView call4()
	{
		ModelAndView mv=new ModelAndView("managestop");
		Stop s=new Stop();
		mv.addObject("stop", s);
		return mv;
		
	}
	
	@GetMapping("/editstop")
	public ModelAndView editing()
	{
		String uri = "http://localhost:8080/stop/getall"; 
		RestTemplate restTemplate = new RestTemplate();
	    List<LinkedHashMap<String,String>> result = restTemplate.getForObject(uri, List.class);
		ModelAndView mv=new ModelAndView("editstop");
		//mv.addObject("stops", result);
		return mv;
	}
	
	
}
