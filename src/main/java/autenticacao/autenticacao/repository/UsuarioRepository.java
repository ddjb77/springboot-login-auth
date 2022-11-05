package autenticacao.autenticacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import autenticacao.autenticacao.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer>{
	
	public Optional<UsuarioModel> findByLogin(String login);

	public static Optional<UsuarioModel> findByLoginAndPassword(String login, String password) {
		
		return null;
	}
	
	
}
