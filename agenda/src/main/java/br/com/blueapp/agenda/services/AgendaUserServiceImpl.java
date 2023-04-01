package br.com.blueapp.agenda.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.blueapp.agenda.entitys.AgendaUser;
import br.com.blueapp.agenda.repositorys.AgendaUserRepository;
import br.com.blueapp.agenda.services.excepcion.EmailCadastradoExcepcion;

@Service
public class AgendaUserServiceImpl {
	
	@Autowired
	AgendaUserRepository userRepository;
	
	
	
	public void cadastrarUsuario(AgendaUser user) throws EmailCadastradoExcepcion {
		
		Optional<AgendaUser> objUsuario = this.userRepository.findByUsername(user.getUsername());
		
		if(objUsuario.isPresent()) {
			throw new EmailCadastradoExcepcion("Usuario Já cadastrado");
		}
		
		if(!objUsuario.isPresent()) {
			String senha = 	new BCryptPasswordEncoder().encode(user.getPassword());
			
			user.setPassword(senha);
		}
		
		
		this.userRepository.save(user);
		
	}
	
	
	public void deletarUsuario(Long id) throws EmailCadastradoExcepcion {
		
		Optional<AgendaUser> objUsuario = this.userRepository.findById(id);
		
		if(objUsuario.isEmpty()) {
			throw new EmailCadastradoExcepcion("Usuario Não cadastrado");
		}
		
		userRepository.deleteById(id);;
	}

}
