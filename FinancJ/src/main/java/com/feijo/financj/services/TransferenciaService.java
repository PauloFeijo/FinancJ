package com.feijo.financj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Transferencia;
import com.feijo.financj.domain.DTO.TransferenciaDTO;
import com.feijo.financj.repositories.TransferenciaRepository;
import com.feijo.financj.services.exceptions.ObjectNotFoundException;

@Service
public class TransferenciaService {

	@Autowired
	TransferenciaRepository repo;
	
	@Autowired
	ContaService contaServ;
	
	@Autowired
	MovimentacaoService movServ;
	
	@Autowired
	UsuarioService userServ;	

	public Transferencia find(Integer id) {
		Transferencia obj = repo.findById(id).orElse(null);
		
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Transferencia.class.getName());
		}		
		return obj;
	}

	public List<Transferencia> findAll() {
		return repo.findByUsuario(userServ.getUsuarioLogado());
	}

	public Transferencia insert(TransferenciaDTO objDto) {
		objDto.setId(null);
		return insertUpdate(objDto);
	}

	public Transferencia update(TransferenciaDTO objDto) {
		return insertUpdate(objDto);
	}
	
	public void delete(Integer id) {
		Transferencia transf = find(id);
		repo.deleteById(id);
		movServ.deleteByTransferencia(transf);
	}
	
	private Transferencia insertUpdate(TransferenciaDTO objDto) {
		Transferencia obj;
		
		if (objDto.getId() != null) {
			obj = find(objDto.getId());
		} else {
			obj = new Transferencia();
		}
		
		updateData(obj, objDto);
		movServ.insertUpdateByTransferencia(obj);
		return repo.save(obj);
	}

	private void updateData(Transferencia obj, TransferenciaDTO objDto) {
		
		Conta origem = contaServ.find(objDto.getOrigemId());
		Conta destino = contaServ.find(objDto.getDestinoId());
		
		obj.setOrigem(origem);
		obj.setDestino(destino);
		obj.setData(objDto.getData());
		obj.setValor(objDto.getValor());
		obj.setUsuario(userServ.getUsuarioLogado());
	}
}
