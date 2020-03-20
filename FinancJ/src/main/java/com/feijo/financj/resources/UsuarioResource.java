package com.feijo.financj.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.feijo.financj.domain.Usuario;
import com.feijo.financj.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService serv;

	@RequestMapping(value = "/{usuario}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable String usuario) {
		
		Usuario obj = serv.find(usuario);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {

		List<Usuario>  lista = serv.findAll();

		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Usuario obj) {
		
		obj = serv.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{usuario}").buildAndExpand(obj.getUsuario()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{usuario}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable String usuario) {
		
		obj = serv.update(obj);

		return ResponseEntity.noContent().build();
	}	
	
	@RequestMapping(value = "/{usuario}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable String usuario) {
		
		serv.delete(usuario);

		return ResponseEntity.noContent().build();
	}	

}
