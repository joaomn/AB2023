package br.com.blueapp.agenda.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.blueapp.agenda.entitys.dtos.AgendaEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AGD_BLUE")
public class AgendaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 200)	
	private String name;

	@Column(name = "telefone")	
	private String phone;

	@Column(name = "email")
	
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public AgendaEntityDTO toDto() {
		return new AgendaEntityDTO(this);
	}

	public AgendaEntity(AgendaEntityDTO dto) {
		this.name = dto.getName();
		this.phone = dto.getPhone();
		this.email = dto.getEmail();
	}

}
