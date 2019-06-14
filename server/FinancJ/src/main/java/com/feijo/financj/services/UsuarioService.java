package com.feijo.financj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Usuario;
import com.feijo.financj.repositories.UsuarioRepository;
import com.feijo.financj.security.UserSS;
import com.feijo.financj.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	public String usuarioLogadoFake;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;		

	public Usuario find(String usuario) {
		Usuario obj = repo.findById(usuario).orElse(null);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + usuario + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario insert(Usuario obj) {
		Usuario newObj = new Usuario();
		updateData(newObj, obj);
		return repo.save(newObj);		
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getUsuario());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(String usuario) {
		find(usuario);
		repo.deleteById(usuario);
	}

	public Usuario getUsuarioLogado() {
		UserSS userss = UserService.authenticated();
		String usuario = userss != null ? userss.getUsername() : usuarioLogadoFake;
		return repo.findById(usuario).orElse(null);
	}	

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setUsuario(obj.getUsuario());
		newObj.setEmail(obj.getEmail());
		newObj.setSenha(pe.encode(obj.getSenha()));
		newObj.setNome(obj.getNome());
	}
}
