package com.gams.storesystem.services;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.domain.Client;
import com.gams.storesystem.dto.CategoryDTO;
import com.gams.storesystem.repositories.CategoryRepository;
import com.gams.storesystem.services.exceptions.DataIntegrityException;
import com.gams.storesystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category search(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj); //save item in db
	}

	public Category update(Category obj) {
		Category newObj = search(obj.getId()); //verify if exists id
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		search(id); 
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Category> findAll() {
		return repo.findAll();
	
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(),objDto.getName());
	}

	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}