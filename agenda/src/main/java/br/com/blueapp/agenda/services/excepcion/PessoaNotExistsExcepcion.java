package br.com.blueapp.agenda.services.excepcion;

public class PessoaNotExistsExcepcion extends Exception {

	private static final long serialVersionUID = 1L;

	public PessoaNotExistsExcepcion(String message) {
		super(message);

	}

}
