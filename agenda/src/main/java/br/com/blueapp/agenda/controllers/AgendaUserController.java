package br.com.blueapp.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blueapp.agenda.entitys.AgendaUser;
import br.com.blueapp.agenda.entitys.dtos.AgendaUserDTO;
import br.com.blueapp.agenda.services.AgendaUserServiceImpl;
import br.com.blueapp.agenda.services.excepcion.EmailCadastradoExcepcion;
import io.swagger.annotations.ApiOperation;



@RestController
@CrossOrigin("*")
@RequestMapping("api/login")
public class AgendaUserController {
	
	
	@Autowired
	private AgendaUserServiceImpl userServico;
	
	
	
	@ApiOperation(value = "Persistir novo login e senha")
	@PostMapping
	public ResponseEntity<AgendaUser> store(@RequestBody AgendaUser userDTO){
		
		//verificar se tudo foi digitado
		
		if(userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) {
			
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
		
		
		if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
			//userDTO.setMessagem("Senha não informada");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		//persistir os dados
		
	
		
		
		try {
			
			this.userServico.cadastrarUsuario(userDTO);
			
		}catch (EmailCadastradoExcepcion e) {
			//userDTO.setMessagem(e.getMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		//userDTO.setMessagem("Usuario e Senha Cadastrado Com Suceso!");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
		
	}
	
	
	@ApiOperation(value = "Deletar usuario")
	@DeleteMapping("/{id}")
	public ResponseEntity<AgendaUser> deletar(@PathVariable Long id){
		
		AgendaUser objAgenda = new AgendaUser();
		
		objAgenda.setUserId(id);
		
		try {
			this.userServico.deletarUsuario(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(objAgenda);
		}catch (Exception e) {
			
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	
		
	}
	
	@GetMapping("/login")
	String login() {
		return "login";
	}

}
