package com.gams.storesystem.resources;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.domain.Product;
import com.gams.storesystem.domain.Request;
import com.gams.storesystem.dto.CategoryDTO;
import com.gams.storesystem.dto.ProductDTO;
import com.gams.storesystem.resources.utils.URL;
import com.gams.storesystem.services.ProductService;
import com.gams.storesystem.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProductResource {

	@Autowired
	private ProductService service; //for find id
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //receive id for show in url
	public ResponseEntity<Product> find(@PathVariable Integer id) { //@Path is to 'linkar' the id above
		Product obj = service.search(id); //connected with @AutowiredCategory service above
		return ResponseEntity.ok().body(obj); //return the response and found obj
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue ="")String name,
			@RequestParam(value="categories", defaultValue ="")String categories,
			@RequestParam(value="page", defaultValue="0") Integer page, //optional param, only if requested
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, //24 for responsive layoyt
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) { //ASC OR DESC => ASCEDENT OR DESCENDENT
		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
