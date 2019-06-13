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
import com.feijo.financj.domain.Movimentacao;
import com.feijo.financj.domain.DTO.MovimentacaoDTO;
import com.feijo.financj.services.MovimentacaoService;

@RestController
@RequestMapping(value = "/movimentacoes")
public class MovimentacaoResource {

	@Autowired
	private MovimentacaoService serv;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		MovimentacaoDTO movimentacao = serv.findDTO(id);
		
		return ResponseEntity.ok().body(movimentacao);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MovimentacaoDTO>> findAll() {

		List<MovimentacaoDTO>  lista = serv.findAll();

		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MovimentacaoDTO objDto) {
		
		Movimentacao obj = serv.insert(objDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MovimentacaoDTO objDto, @PathVariable Integer id) {
		
		serv.update(objDto);

		return ResponseEntity.noContent().build();
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		serv.delete(id);

		return ResponseEntity.noContent().build();
	}	

}
