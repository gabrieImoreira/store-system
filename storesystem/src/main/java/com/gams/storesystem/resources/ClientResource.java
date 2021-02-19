package com.gams.storesystem.resources;

import com.gams.storesystem.domain.Client;
import com.gams.storesystem.dto.CategoryDTO;
import com.gams.storesystem.dto.ClientDTO;
import com.gams.storesystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id) {
		Client obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> list = service.findAll();
		List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList()); //convert list to DTO in one line
		return ResponseEntity.ok().body(listDto);
	}

	// ->      /categorias/page?page=0&linesPerPage=20
	@RequestMapping(value="page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, //optional param, only if requested
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, //24 for responsive layoyt
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) { //ASC OR DESC => ASCEDENT OR DESCENDENT
		Page<Client> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
