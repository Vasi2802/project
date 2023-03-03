package org.antwalk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antwalk.entity.ArrivalTimeTable;
import org.antwalk.entity.Route;
import org.antwalk.entity.RouteStop;
import org.antwalk.entity.Stop;
import org.antwalk.repository.ArrivalTimeRepo;
import org.antwalk.repository.RouteRepo;
import org.antwalk.repository.StopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arrivaltime")
public class ArrivalTimeController {

	@Autowired
	ArrivalTimeRepo arrivalTimeRepo;

	@Autowired
	RouteRepo routeRepo;

	@Autowired
	StopRepo stopRepo;

	@PostMapping("/insert")
	public ArrivalTimeTable insert(@RequestBody ArrivalTimeTable at) {
		return arrivalTimeRepo.save(at);
	}

	@GetMapping("/getall")
	public List<ArrivalTimeTable> getAll() {
		return arrivalTimeRepo.findAll();
	}

	@GetMapping("/getbyid/{rid}/{sid}")
	public ArrivalTimeTable getById(@PathVariable long rid, @PathVariable long sid) {
		// return arrivalTimeRepo.findById(new RouteAndStop(rid, sid)).get();
		return arrivalTimeRepo.findById(new RouteStop(routeRepo.findById(rid).get(), stopRepo.findById(sid).get()))
				.get();
	}

	@DeleteMapping("/deletebyid/{rid}/{sid}")
	public String deleteById(@PathVariable long rid, @PathVariable long sid) {
		arrivalTimeRepo.deleteById(new RouteStop(routeRepo.findById(rid).get(), stopRepo.findById(sid).get()));
		return "Deleted";

	}

	@PutMapping("/update/{rid}/{sid}")
	public String update(@RequestBody ArrivalTimeTable at, @PathVariable long rid, @PathVariable long sid) {
		List<ArrivalTimeTable> atList = arrivalTimeRepo.findAll();
		for (ArrivalTimeTable obj : atList) {
			if (obj.getRouteStop().equals(new RouteStop(routeRepo.findById(rid).get(), stopRepo.findById(sid).get()))) {
				if (at.getRouteStop().equals(new RouteStop(routeRepo.findById(rid).get(), stopRepo.findById(sid).get()))) {
					arrivalTimeRepo.save(at);
					return "Updated";
				}

				else {
					return "Id doesn't match";
				}

			}
		}
		return "Id does not exist";

	}

	/* Rest APIs to serve business logic */

	// get all routes as list of stops which have the stop with stopId
	@GetMapping("/getallroutes/{stopId}") 
	public List<Object> getRoutesByStopId(@PathVariable long stopId){


		List<ArrivalTimeTable> arrivalTimeTables = arrivalTimeRepo.findAllByRouteStop_Stop(new Stop(1, "Stop1"));
		
		List<Object> routes = new ArrayList();
		HashMap<String, Object> route = new HashMap<>();
		List<Stop> stops = new ArrayList();  // temporary list of stops. to be fetched
		stops.add(new Stop(1, "Stop1"));
		stops.add(new Stop(2, "Stop2"));
		stops.add(new Stop(3, "Stop3"));
		stops.add(new Stop(4, "Stop4"));
		stops.add(new Stop(5, "Stop5"));

		route.put("route", new Route(1, stops.get(0), stops.get(3), "true"));
		route.put("stops", stops);

		routes.add(route);
		routes.add(route.clone());
		return routes;
	}

	@GetMapping("/getallstops/{routeId}")
	public List<Stop> getStopsByRouteId(@PathVariable long routeId){
		List<Stop> stops = new ArrayList(); // temporary list of stops. to be fetched
		stops.add(new Stop(1, "Stop1"));
		stops.add(new Stop(2, "Stop2"));
		stops.add(new Stop(3, "Stop3"));
		stops.add(new Stop(4, "Stop4"));
		stops.add(new Stop(5, "Stop5"));
		return stops;
	}
}
