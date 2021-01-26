package com.gams.storesystem.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gams.storesystem.domain.Client;
import com.gams.storesystem.services.ClientService;

@RestController
@RequestMapping(value="/clientes")
public class ClientResource {

	@Autowired
	private ClientService service; //for find id
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //receive id for show in url
	public ResponseEntity<Client> find(@PathVariable Integer id) { //@Path is to 'linkar' the id above
		Client obj = service.search(id); //connected with @AutowiredClient service above 
		return ResponseEntity.ok().body(obj); //return the response and found obj 

	}
}
