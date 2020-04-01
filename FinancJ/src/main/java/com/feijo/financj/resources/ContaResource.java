package com.feijo.financj.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.feijo.financj.domain.Conta;
import com.feijo.financj.services.ContaService;

@RestController
@RequestMapping(value = "/conta")
public class ContaResource {

	@Autowired
	private ContaService serv;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Conta conta = serv.find(id);
		
		return ResponseEntity.ok().body(conta);
	}
	
	@GetMapping
	public ResponseEntity<List<Conta>> findAll() {

		List<Conta>  lista = serv.findAll();

		return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Conta obj) {
		
		obj = serv.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Conta obj, @PathVariable Integer id) {
		
		obj.setId(id);
		obj = serv.update(obj);

		return ResponseEntity.noContent().build();
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		serv.delete(id);

		return ResponseEntity.noContent().build();
	}	

}
