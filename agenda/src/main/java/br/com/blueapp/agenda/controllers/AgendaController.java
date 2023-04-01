package br.com.blueapp.agenda.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blueapp.agenda.entitys.AgendaEntity;
import br.com.blueapp.agenda.entitys.dtos.AgendaEntityDTO;
import br.com.blueapp.agenda.services.AgendaServiceImpls;
import br.com.blueapp.agenda.services.excepcion.EmailCadastradoExcepcion;
import br.com.blueapp.agenda.services.excepcion.PessoaNotExistsExcepcion;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/agenda")
public class AgendaController {

	@Autowired
	private AgendaServiceImpls servico;

	@ApiOperation(value = "Persistir no banco de dados")
	@PostMapping
	public ResponseEntity<AgendaEntityDTO> salvar(@RequestBody AgendaEntityDTO dto) {

		if (null == dto.getEmail() || dto.getEmail().isEmpty()) {
			dto.setMenssagem("Email não informado");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

		}

		if (null == dto.getName() || dto.getName().equals("")) {
			dto.setMenssagem("Nome não Informado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		if (null == dto.getPhone() || dto.getPhone().equals("")) {
			dto.setMenssagem("Telefone não Informado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		Pattern pattern = Pattern.compile(regex);

		if (!pattern.asMatchPredicate().test(dto.getEmail())) {
			dto.setMenssagem("Email Invalido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

		}

		// persistir dados

		AgendaEntity agenda = new AgendaEntity(dto);

		try {

			this.servico.salvar(agenda);

		} catch (EmailCadastradoExcepcion e) {
			dto.setMenssagem(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

		}

		dto.setMenssagem("Contato cadastrado com Sucesso!");
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}

	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<AgendaEntityDTO>> index() {

		List<AgendaEntityDTO> agendaDTO = new ArrayList<>();

		List<AgendaEntity> agendas = this.servico.buscartudo();
		if (!agendas.isEmpty()) {
			agendaDTO = agendas.stream().map(agendaentity -> agendaentity.toDto()).collect(Collectors.toList());

		}

		return ResponseEntity.status(HttpStatus.OK).body(agendaDTO);
	}

	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<AgendaEntityDTO> show(@PathVariable Long id) {

		Optional<AgendaEntity> pessoa = this.servico.buscarPessoa(id);

		if (pessoa.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(pessoa.get().toDto());

		}

		AgendaEntityDTO agendaDTO = new AgendaEntityDTO();
		agendaDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(agendaDTO);

	}

	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<AgendaEntityDTO> update(@PathVariable Long id, @RequestBody AgendaEntityDTO dto)  {

		Optional<AgendaEntity> objeto = this.servico.buscarPessoa(id);

		if (objeto.isPresent()) {
			if (null == dto.getEmail() || dto.getEmail().isEmpty()) {
				dto.setMenssagem("Email não informado");

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

			}

			if (null == dto.getName() || dto.getName().equals("")) {
				dto.setMenssagem("Nome não Informado");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}

			if (null == dto.getPhone() || dto.getPhone().equals("")) {
				dto.setMenssagem("Telefone não Informado");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}
			
			
			String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

			Pattern pattern = Pattern.compile(regex);

			if (!pattern.asMatchPredicate().test(dto.getEmail())) {
				dto.setMenssagem("Email Invalido");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
				}
			
			
			servico.update(id, dto);
			return ResponseEntity.status(HttpStatus.OK).body(objeto.get().toDto());
		}

		AgendaEntityDTO agendaDTO = new AgendaEntityDTO();
		agendaDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(agendaDTO);

	}

	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<AgendaEntityDTO> delete(@PathVariable Long id) {

		AgendaEntityDTO objDTO = new AgendaEntityDTO();

		objDTO.setId(id);

		try {
			this.servico.delete(objDTO.getId());
			objDTO.setMenssagem("Contato excluido com sucesso!");
			return ResponseEntity.status(HttpStatus.OK).body(objDTO);

		} catch (PessoaNotExistsExcepcion e) {
			objDTO.setMenssagem(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objDTO);
		}

	}

}
