package org.serratec.service;

import java.util.Optional;

import org.serratec.model.Usuario;
import org.serratec.repository.UsuarioRepository;
import org.serratec.security.UsuarioDetalhe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalheImplService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioDetalheImplService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.BuscarPorEmail(email);
		if (!usuario.isPresent()) {
			throw new RuntimeException();
		}

		return new UsuarioDetalhe(usuario);
	}

}
