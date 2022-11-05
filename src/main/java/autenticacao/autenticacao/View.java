package autenticacao.autenticacao;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class View implements WebMvcConfigurer{

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/login").setViewName("/login");
		registry.addViewController("/cadastrar").setViewName("/cadastrar");
		registry.addViewController("/perfil").setViewName("perfil");
		registry.addViewController("/sucesso").setViewName("/sucesso");
		
		
	}

}

