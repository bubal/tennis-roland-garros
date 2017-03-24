package com.app.tennis.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.TypeQualification;
import com.app.tennis.services.TypeQualificationService;

@RestController
@RequestMapping(value="/api/qualifications")
@CrossOrigin
public class TypeQualificationRest {

	@Autowired
	TypeQualificationService qualificationService;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<TypeQualification> get(){
		return qualificationService.listAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public TypeQualification get(@PathVariable int id){
		return qualificationService.findById(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void del(@PathVariable int id){
		qualificationService.deleteById(id);
	}

	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void add( @RequestParam("nom") String nom){
		TypeQualification obj = new TypeQualification(nom);
		obj = qualificationService.create(obj);
	}

}
