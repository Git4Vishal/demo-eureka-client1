package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@PropertySource("classpath:application.yml")
public class ServiceRestController {
	@Value("${app.instance-id}")
	String instanceID;

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, 
			method = { RequestMethod.GET })
	public List<Patient> findPatient() {
		List<Patient> result = new ArrayList<>();
		result.add(this.buildPatient("1", "First1", "Last1"));
		result.add(this.buildPatient("2", "First2", "Last2"));
		return result;
	}

	@RequestMapping(value = "/{id}", 
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			method = { RequestMethod.GET })
	public Patient getActor(@PathVariable String id) {
		return this.buildPatient(id, String.format("First%s", id), String.format("Last%s", id));
	}

	private Patient buildPatient(String id, String firstName, String lastName) {
		Patient result = new Patient();
		result.setInstanceID(instanceID);
		result.setPatientID(id);
		result.setFirstName(firstName);
		result.setLastName(lastName);
		return result;
	}
}
