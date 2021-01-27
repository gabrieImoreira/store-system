package com.gams.storesystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.repositories.CategoryRepository;
import com.gams.storesystem.services.exceptions.DataIntegrityException;
import com.gams.storesystem.services.exceptions.ObjectNotFoundException;

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
		search(obj.getId()); //verify if exists id
		return repo.save(obj); 
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
}