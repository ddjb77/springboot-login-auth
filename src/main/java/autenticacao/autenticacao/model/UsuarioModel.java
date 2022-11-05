package autenticacao.autenticacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table	(name="Usuario", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
	})

public class UsuarioModel {

    @Id
    @Column(name = "id")
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    private String username;    
    
    @NonNull
    @Column(unique = true)
    private String login;
    
    
    
    
    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    @Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;
 
	private String email;
    
	
    public void setId(Long id) {
		this.id = id;
	}


	public UsuarioModel() {
	}
    
    
    public UsuarioModel(String login,String username, String email, String password) {
		this.username = username;
		this.setEmail(email);
		this.login = login;
		this.password = password;
		this.getRole();
	}
    
    
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/*public  Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		UsuarioModel.roles = roles;
	}*/
	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}



	public autenticacao.autenticacao.model.Role getRole() {
		return role;
	}


	public void setRole(autenticacao.autenticacao.model.Role role) {
		this.role = role;
	}


}