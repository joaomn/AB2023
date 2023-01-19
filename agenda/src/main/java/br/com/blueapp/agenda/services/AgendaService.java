package br.com.blueapp.agenda.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.blueapp.agenda.controllers.AgendaController;
import br.com.blueapp.agenda.entitys.AgendaEntity;
import br.com.blueapp.agenda.entitys.dtos.AgendaEntityDTO;
import br.com.blueapp.agenda.repositorys.AgendaRepository;
import br.com.blueapp.agenda.services.excepcion.EmailCadastradoExcepcion;
import br.com.blueapp.agenda.services.excepcion.PessoaNotExistsExcepcion;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository repository;

	public void salvar(AgendaEntity agenda) throws EmailCadastradoExcepcion {

		// verificar se já existe dado persisitido
		Optional<AgendaEntity> objAgendaemail = this.repository.findContatoByEmail(agenda.getEmail());
		Optional<AgendaEntity> objAgendanome = this.repository.findContatoByName(agenda.getName());
		Optional<AgendaEntity> objAgendaphone = this.repository.findContatoByPhone(agenda.getPhone());

		if (objAgendaemail.isPresent()) {
			throw new EmailCadastradoExcepcion("Email já é Cadastrado");
		}
		if (objAgendanome.isPresent()) {
			throw new EmailCadastradoExcepcion("Usuario já é Cadastrado");
		}

		if (objAgendaphone.isPresent()) {
			throw new EmailCadastradoExcepcion("Este telefone já é Cadastrado");
		}

		this.repository.save(agenda);

	}

	public List<AgendaEntity> buscartudo() {
		return this.repository.findAll();
	}

	public Optional<AgendaEntity> buscarPessoa(Long id) {

		return this.repository.findById(id);

	}

	public  AgendaEntityDTO update (Long id, AgendaEntityDTO agenda) {
		
		Optional<AgendaEntity> objAgenda = repository.findById(id);
		if(objAgenda.isPresent()) {
			AgendaEntity objeto = objAgenda.get();
			if(agenda.getName() != null) {
				objeto.setName(agenda.getName());
			}
			if(agenda.getEmail() != null) {
				objeto.setEmail(agenda.getEmail());
			}
			
			if(agenda.getPhone() != null) {
				objeto.setPhone(agenda.getPhone());
			}
			
			repository.save(objeto);
		}
		
		
	return null;
	
	}
	
	
	public void delete(Long id) throws PessoaNotExistsExcepcion{
		Optional<AgendaEntity> objAgenda = this.repository.findById(id);
		
		
		if(objAgenda.isEmpty()) {
			throw new PessoaNotExistsExcepcion("Usuario não cadastrado.");
		}
		
		repository.deleteById(id);
		
	}
	
	
	

}
