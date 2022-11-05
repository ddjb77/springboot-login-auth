package autenticacao.autenticacao.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import autenticacao.autenticacao.data.DetalheUsuarioData;
import autenticacao.autenticacao.model.Role;
import autenticacao.autenticacao.model.UsuarioModel;

public class JwtAutenticarFilter extends UsernamePasswordAuthenticationFilter{
	
	public static final int TOKEN_EXPIRACAO = 6_000_000;
	
	public static final String TOKEN_SENHA = "d0794206-65be-42ef-86db-c96dfdc2173c";
	
	private final AuthenticationManager authenticationManager;
	
	public JwtAutenticarFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) 
	{
		
		try {
		UsuarioModel usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);
		
				return authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(
						usuario.getLogin(),
						usuario.getPassword()
						
				));		
		
			}		catch (IOException e) {
				throw new RuntimeException("Falha ao autenticar usuário", e);}
		}
	

@Override
protected void successfulAuthentication(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authResult) throws IOException, ServletException {

    DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
    
    String token = JWT.create().withSubject(usuarioData.getUsername())
    							.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
    							.sign(Algorithm.HMAC512(TOKEN_SENHA));
    
    response.getWriter().write(token);
    response.getWriter().flush();
}

}
	
