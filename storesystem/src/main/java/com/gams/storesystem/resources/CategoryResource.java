package com.gams.storesystem.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.services.CategoryService;

@RestController
@RequestMapping(value="/categorias")
public class CategoryResource {

	@Autowired
	private CategoryService service; //for find id
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //receive id for show in url
	public ResponseEntity<Category> find(@PathVariable Integer id) { //@Path is to 'linkar' the id above
		Category obj = service.search(id); //connected with @AutowiredCategory service above 
		return ResponseEntity.ok().body(obj); //return the response and found obj 

	}
	
	@RequestMapping(method=RequestMethod.POST) // create new item 
	public ResponseEntity<Void> insert(@RequestBody Category obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //create new item in category
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();	
		
	}
}
