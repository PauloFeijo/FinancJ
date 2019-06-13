package com.feijo.financj.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feijo.financj.domain.DTO.PagarFaturaDTO;
import com.feijo.financj.services.CartaoCreditoService;

@RestController
@RequestMapping(value = "/pagarFatura")
public class PagarFaturaResource {

	@Autowired
	private CartaoCreditoService serv;	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> pagarFatura(@Valid @RequestBody PagarFaturaDTO obj) {
		
		serv.pagarFatura(obj);
		
		return ResponseEntity.noContent().build();
	}	
}
