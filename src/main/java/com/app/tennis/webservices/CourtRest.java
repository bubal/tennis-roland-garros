package com.app.tennis.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.Court;
import com.app.tennis.services.CourtService;

@RestController
@RequestMapping(value="/api/courts")
public class CourtRest {

	@Autowired
	CourtService serviceCourt;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Court> getCourts(){
		return serviceCourt.listAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Court getCourt(@PathVariable int id){
		return serviceCourt.findById(id);
	}



}
