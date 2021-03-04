package com.gams.storesystem.resources;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gams.storesystem.domain.Request;
import com.gams.storesystem.services.RequestService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/pedidos")
public class RequestResource {

	@Autowired
	private RequestService service; //for find id
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //receive id for show in url
	public ResponseEntity<Request> find(@PathVariable Integer id) { //@Path is to 'linkar' the id above
		Request obj = service.search(id); //connected with @AutowiredCategory service above 
		return ResponseEntity.ok().body(obj); //return the response and found obj 

	}

	@RequestMapping(method=RequestMethod.POST) // create new item
	public ResponseEntity<Void> insert(@Valid @RequestBody Request obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //create new item in category
	}
}
