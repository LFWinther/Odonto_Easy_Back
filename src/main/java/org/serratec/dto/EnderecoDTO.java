package org.serratec.dto;

import org.serratec.model.Cep;
import org.serratec.model.Endereco;

public class EnderecoDTO {

    private Cep cep;
    private String complemento;
	private String numero;
	
	public EnderecoDTO(Endereco endereco) {
		this.cep = endereco.getCep();
		this.complemento = endereco.getComplemento();
		this.numero = endereco.getNumero();
	}

	public Cep getCep() {
		return cep;
	}
	public void setCep(Cep cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
