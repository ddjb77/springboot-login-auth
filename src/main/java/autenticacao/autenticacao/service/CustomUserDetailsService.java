/*package autenticacao.autenticacao.service;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import autenticacao.autenticacao.model.UsuarioModel;
import autenticacao.autenticacao.repository.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	
	private UsuarioRepository usuarioRepository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioModel usuario = usuarioRepository.findByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario[" + username + "] não encontrado!");
		}
		
		return new org.springframework.security.core.userdetails.User
				
				
				(
						usuario.getEmail(),
						usuario.getPassword(),
						usuario.isEnabled(),
						true,
						true,
						true,
						getAuthorities(List.of(usuario.getRole()))	
						
				);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String role: roles) {
			authorities.add(new SimpleGrantedAuthority(role)); 
		}
		return authorities;
	}

}
*/