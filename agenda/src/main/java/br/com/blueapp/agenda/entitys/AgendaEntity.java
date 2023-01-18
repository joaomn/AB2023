package br.com.blueapp.agenda.entitys;

import java.io.Serializable;

import br.com.blueapp.agenda.entitys.dtos.AgendaEntityDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
