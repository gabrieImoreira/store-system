package com.gams.storesystem.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.services.CategoryService;

@RestController
@RequestMapping(value="/categorias")
public class CategoryResource {

	@Autowired
	private CategoryService service; //for find id
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //receive id for show in url
	public ResponseEntity<?> find(@PathVariable Integer id) { //@Path is to 'linkar' the id above
		Category obj = service.search(id); //connected with @AutowiredCategory service above 
		return ResponseEntity.ok().body(obj); //return the response and found obj 

	}
}
