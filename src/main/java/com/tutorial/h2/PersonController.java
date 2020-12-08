package com.tutorial.h2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

   /* @GetMapping("/persons")
    private List<Person> getAllPersons() {
        return personService.getAllPersons();
    }*/
    
    @GetMapping("/persons")
    private String getAllPersonsPage(Model model) {
    	List<Person> people = personService.getAllPersons();
    	for(Person p :people) {
    		System.out.println(p.toString());
    	}
    	if(people.size()==0) {
    		System.out.println("NO ONE IN PEOPLE");
    	}
    	model.addAttribute("peopleList", people);
    	return "persons";
    }

   /* @GetMapping("/persons/{id}")
    private Person getPerson(@PathVariable("id") int id) {
        return personService.getPersonById(id);
    }*/
    
    @GetMapping("/persons/{id}")
    private String getPerson(@PathVariable("id") int id, Model model) {
    	Person p = personService.getPersonById(id);
    	model.addAttribute("peep", p);
    	return "person";
    }

    @DeleteMapping("/persons/{id}")
    private void deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
    }

    @PostMapping("/persons")
    private int savePerson(@RequestBody Person person) {
        personService.saveOrUpdate(person);
        return person.getId();
    }
}