package com.gams.storesystem.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.dto.CategoryDTO;
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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() { 
		List<Category> list = service.findAll(); 
		List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList()); //convert list to DTO in one line
		return ResponseEntity.ok().body(listDto);
	}
	
	// ->      /categorias/page?page=0&linesPerPage=20
	@RequestMapping(value="page", method=RequestMethod.GET) 
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, //optional param, only if requested
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, //24 for responsive layoyt
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) { //ASC OR DESC => ASCEDENT OR DESCENDENT
		Page<Category> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj)); 
		return ResponseEntity.ok().body(listDto); 
	}
	
}
