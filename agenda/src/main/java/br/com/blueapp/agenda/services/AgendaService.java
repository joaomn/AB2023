package br.com.blueapp.agenda.services;

import java.util.List;
import java.util.Optional;

import br.com.blueapp.agenda.entitys.AgendaEntity;
import br.com.blueapp.agenda.entitys.dtos.AgendaEntityDTO;
import br.com.blueapp.agenda.services.excepcion.EmailCadastradoExcepcion;
import br.com.blueapp.agenda.services.excepcion.PessoaNotExistsExcepcion;

public interface AgendaService {
	 void salvar(AgendaEntity agenda) throws EmailCadastradoExcepcion;
	 
	 List<AgendaEntity> buscartudo();
	 
	 Optional<AgendaEntity> buscarPessoa(Long id);
	 
	 AgendaEntityDTO update(Long id, AgendaEntityDTO agenda);
	 
	 void delete(Long id) throws PessoaNotExistsExcepcion;
	

}
