package autenticacao.autenticacao.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import autenticacao.autenticacao.model.Role;
import autenticacao.autenticacao.model.UsuarioModel;

public class DetalheUsuarioData implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Optional<UsuarioModel> usuario;
	
	 public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public void setUsername(String username) {
		this.username = username;
	}



	private Long id;

	private String username;


	
	private String email;
	  
	@JsonIgnore
	private String password;
	
	public DetalheUsuarioData(Optional<UsuarioModel> usuario ) {
		this.usuario = usuario;
	}
	

	
	public Collection<? extends GrantedAuthority> authorities;
	
	
	public DetalheUsuarioData(Long id, String username, String email, String password,
		    Set<Role> set, Collection<? extends GrantedAuthority> authorities) {
		    this.id = id;
		    this.username = username;
		    this.email = email;
		    this.password = password;
		    this.authorities = authorities;
			this.usuario = null;
		  }
		
	/*public static DetalheUsuarioData build(UsuarioModel usuario) {
	    List<GrantedAuthority> authorities = usuario.getRoles().stream()
	        .map(role -> new SimpleGrantedAuthority(role.getName()))
	        .collect(Collectors.toList());
	    return new DetalheUsuarioData(
	            usuario.getId(), 
	            usuario.getUsername(), 
	            usuario.getEmail(),
	            usuario.getPassword(),

	            authorities);
	      }*/
	
	



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.orElse(new UsuarioModel()).getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.orElse(new UsuarioModel()).getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	
	
	
	 @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    DetalheUsuarioData usuario = (DetalheUsuarioData) o;
	    return Objects.equals(id, usuario.id);
	  }






	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	

}
