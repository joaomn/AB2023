package br.com.blueapp.agenda.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.blueapp.agenda.entitys.AgendaEntity;
import br.com.blueapp.agenda.services.excepcion.EmailCadastradoExcepcion;
import br.com.blueapp.agenda.services.excepcion.PessoaNotExistsExcepcion;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AgendaServiceTest {

	@Autowired
	private AgendaService agendaService;

	private static AgendaEntity novoUsuario1;
	private static AgendaEntity novoUsuario2;
	private static AgendaEntity novoUsuario3;
	private static AgendaEntity novoUsuario4;

	@BeforeAll
	static void setup() {

		novoUsuario1 = new AgendaEntity();

		novoUsuario1.setEmail("email1@teste");
		novoUsuario1.setName("Fulano de tal1");
		novoUsuario1.setPhone("123");
		novoUsuario1.setId(1L);

		novoUsuario2 = new AgendaEntity();

		novoUsuario2.setEmail("email2@teste.com");
		novoUsuario2.setName("Fulano de tal2");
		novoUsuario2.setPhone("1234");
		novoUsuario2.setId(2L);

		novoUsuario3 = new AgendaEntity();

		novoUsuario3.setEmail("email3@teste.com");
		novoUsuario3.setName("Fulano de tal3");
		novoUsuario3.setPhone("12345");
		novoUsuario3.setId(3L);

		novoUsuario4 = new AgendaEntity();

		novoUsuario4.setEmail("email4@teste.com");
		novoUsuario4.setName("Fulano de tal4");
		novoUsuario4.setPhone("123456");
		novoUsuario4.setId(4L);

	}

	@DisplayName("Persisitir dados novos")
	@Order(1)
	@Test
	public void salvarNovoTeste() throws EmailCadastradoExcepcion {

		this.agendaService.salvar(novoUsuario1);

		Optional<AgendaEntity> user = this.agendaService.buscarPessoa(novoUsuario1.getId());

		// verificar se foi persistido
		assertTrue(user.isPresent());

	}

	@DisplayName("Falhar ao persisitr")
	@Order(2)
	@Test
	public void salvarNovoTeste_falha() {

		
		
		Exception exception = assertThrows(EmailCadastradoExcepcion.class, () -> {
			this.agendaService.salvar(novoUsuario1);			
		});
		
		assertTrue(exception.getMessage()
				.contains("cadastrado"));

	}

	@DisplayName("alterar pessoa existente com sucesso")
	@Order(3)
	@Test
	public void updateContatoExistente() throws  EmailCadastradoExcepcion{
		this.agendaService.salvar(novoUsuario1);
		
		novoUsuario1.setName("Joao");
		
		this.agendaService.update(novoUsuario1.getId(), novoUsuario1.toDto());
		
		Optional<AgendaEntity> contatoBanco = this.agendaService.buscarPessoa(novoUsuario1.getId());
		
		assertTrue(contatoBanco.isPresent());
		
		assertTrue(contatoBanco.get().getId().equals(novoUsuario1.getId()));
		
		
	}
	
}
