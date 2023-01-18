package br.com.blueapp.agenda.entitys.dtos;

import br.com.blueapp.agenda.entitys.AgendaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgendaEntityDTO {
	
	private Long id;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String menssagem;

	public AgendaEntityDTO(AgendaEntity agenda) {
		
		this.name = agenda.getName();
		this.phone = agenda.getPhone();
		this.email = agenda.getEmail();
		this.id = agenda.getId();
	}
	

}
