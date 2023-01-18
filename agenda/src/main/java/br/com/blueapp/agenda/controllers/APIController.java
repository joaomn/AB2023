package br.com.blueapp.agenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import br.com.blueapp.agenda.entitys.dtos.MenssagemDTO;
//import io.swagger.annotations.ApiOperation;
//import springfox.documentation.annotations.ApiIgnore;

public class APIController {
	
	//@ApiIgnore
	@GetMapping()
	public RedirectView redirectSwagger() {
		return new RedirectView("/swagger-ui.html");		
	}
	
	//@ApiOperation(value="Verifica disponibilidade do serviço api do sistema Café")
	@GetMapping(value="api", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MenssagemDTO> index() {
		
		MenssagemDTO dto = new MenssagemDTO(HttpStatus.OK.value(), "Api do Sistema Café está online!");
		dto.setCaminho("/api");
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

}
