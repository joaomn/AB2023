package br.com.blueapp.agenda.entitys.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenssagemDTO {

	private int status;

	private String mensagem;

	

	public MenssagemDTO(int status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}

}
