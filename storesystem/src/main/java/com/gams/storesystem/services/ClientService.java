package com.gams.storesystem.services;

import com.gams.storesystem.domain.Address;
import com.gams.storesystem.domain.Category;
import com.gams.storesystem.domain.City;
import com.gams.storesystem.domain.Client;
import com.gams.storesystem.domain.enums.TypeClient;
import com.gams.storesystem.dto.ClientDTO;
import com.gams.storesystem.dto.ClientNewDto;
import com.gams.storesystem.repositories.AddressRepository;
import com.gams.storesystem.repositories.ClientRepository;
import com.gams.storesystem.services.exceptions.DataIntegrityException;
import com.gams.storesystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	@Autowired
	private AddressRepository addressRepository;

	public Client search(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return obj; //save item in db
	}

	public Client update(Client obj) {
		Client newObj = search(obj.getId()); //verify if exists id
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		search(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pq há entidades relacionadas");
		}
	}

	public List<Client> findAll() {
		return repo.findAll();

	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}

	public Client fromDTO(ClientNewDto objDto) {

		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(),
				TypeClient.toEnum(objDto.getType()));
		City city = new City(objDto.getCityId(), null, null);
		Address addr = new Address(null, objDto.getPublicArea(), objDto.getNumber(), objDto.getComplement(),
				objDto.getDistrict(), objDto.getZipCode(), cli, city);
		cli.getAdresses().add(addr);
		cli.getPhones().add(objDto.getPhone1());
		if(objDto.getPhone2() != null){
			cli.getPhones().add(objDto.getPhone2());
		}
		if(objDto.getPhone3() != null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}


	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}