package autenticacao.autenticacao.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import autenticacao.autenticacao.model.UsuarioModel;
import autenticacao.autenticacao.repository.UsuarioRepository;
import autenticacao.autenticacao.service.DetalheUsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    
    private final PasswordEncoder encoder;
    

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
        
    }
    
    

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario) {
    	usuario.getRole();
    	usuario.getId();
    	usuario.setPassword(encoder.encode(
    	usuario.getPassword()));
    	usuario.getRole();
    	
    	
        ResponseEntity.ok(repository.save(usuario));
		return ResponseEntity .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "./sucesso.html")
                .build();
				

    }
    
    
    @PostMapping("/login")
	 public String login(@ModelAttribute UsuarioModel usuario, Model model) {
		 System.out.println("loginRequest:" + usuario);
		 UsuarioModel authenticated = DetalheUsuarioServiceImpl.authenticate(login(usuario, model), null);
		 if (authenticated != null) {
			 model.addAttribute("usuario", authenticated.getLogin());
			 return "perfil";
		 }else {
			 return "error";
		 }
    
    }
		 
		 
		 
	public PasswordEncoder getEncoder() {
		return encoder;
	}
	
	@GetMapping("/validarsenha")
	
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password){
	Optional<UsuarioModel> optUsuario = repository.findByLogin(login);
    if (optUsuario.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false) ;
    }

    UsuarioModel usuario = optUsuario.get();
    boolean valid = encoder.matches(password, usuario.getPassword());

    HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
    return ResponseEntity.status(status).body(valid);

}


	
	
}
    
