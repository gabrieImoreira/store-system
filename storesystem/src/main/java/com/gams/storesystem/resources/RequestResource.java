package com.gams.storesystem.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gams.storesystem.domain.Request;
import com.gams.storesystem.services.RequestService;

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
}
