package saulo.io.projetoPercistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import saulo.io.projetoPercistence.entity.Perfil;
import saulo.io.projetoPercistence.entity.Usuario;
import saulo.io.projetoPercistence.enun.StatusPerfil;
import saulo.io.projetoPercistence.repository.PerfilRepository;
import saulo.io.projetoPercistence.repository.UsuarioRepository;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		/*
		 * Abaixo existe uma função funcional, más sem uma percistencia
		 * 
		 * Perfil perfil = new Perfil("ADMIN", StatusPerfil.ATIVO);
		 * 
		 * this.perfilRepository.save(perfil); // Sem percistencia
		 * 
		 */

		/*
		 * Agora incluindo a configuração da percistencia na entidade a percistencia
		 * passa a existir sem a necesidade do SAVE
		 */

		Perfil perfil = new Perfil("ADMIN", StatusPerfil.ATIVO);

		Usuario usuario = new Usuario();
		usuario.setNome("Saulo Costa");
		usuario.setEmail("saulo.costa@");
		usuario.setPerfil(perfil);

		this.usuarioRepository.save(usuario);

		List<Usuario> user = this.usuarioRepository.findAll();

		for (Usuario teste : user) {

			System.out.println(perfil.getStatusPerfil().name());
		}
	}

}
