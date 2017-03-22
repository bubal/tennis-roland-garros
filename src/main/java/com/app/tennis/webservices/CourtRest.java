package com.app.tennis.webservices;

import java.util.List;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.Court;
import com.app.tennis.services.CourtService;

@RestController
@RequestMapping(value="/api/courts")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class CourtRest {

	@Autowired
	private CourtService courtService;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Court> get(){
		return courtService.listAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Court get(@PathVariable int id){
		return courtService.findById(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void del(@PathVariable int id){
		courtService.deleteById(id);
	}

	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void add(@RequestParam("nom") String nom){
		Court obj = new Court(nom);
		obj = courtService.create(obj);
	}

}
