package autenticacao.autenticacao.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import autenticacao.autenticacao.data.DetalheUsuarioData;
import autenticacao.autenticacao.model.UsuarioModel;
import autenticacao.autenticacao.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService{
	
	private final UsuarioRepository repository;
	
	public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public UsuarioRepository getRepository() {
		return repository;
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<UsuarioModel> usuario = repository.findByLogin(username);
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuario[" + username + "] não encontrado!");
		}
		return new DetalheUsuarioData(usuario);
	}
	
	
	public static UsuarioModel authenticate(String login, String password) {
		return UsuarioRepository.findByLoginAndPassword(login, password).orElse(null);
	}
	
	 private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
	        List<GrantedAuthority>  authorities = new ArrayList<>();
	        for(String role: roles) {
	            authorities.add(new SimpleGrantedAuthority(role));
	        }
	        return authorities;
	    }
	
	 

}
