package org.serratec.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.model.UsuarioPerfil;
import org.serratec.model.Usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioInserirDTO {
    
    @Size(max = 40, min = 2)
    private String nome;
   
    @CPF(message = "CPF Inválido")
    private String cpf;

    @Size(max = 40)
    @Email(message = "E-mail inválido")
    private String email;
    
    @Size(max = 255)
    private String password;
    
    private String cep;
    
    @Size(max = 200)
    private String complemento;
    
    @Size(max = 40)
    private String numero;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;
    
    @Size(max = 20)
    private String telefone;
    
    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
    
    public UsuarioInserirDTO(){

    }

    public UsuarioInserirDTO(Usuario usuario){
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.cep = usuario.getEndereco().getCep().getCep();
        this.complemento = usuario.getEndereco().getComplemento();
        this.numero = usuario.getEndereco().getNumero();
        this.nascimento = usuario.getNascimento();
        this.telefone = usuario.getTelefone();
    }
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	public Set<UsuarioPerfil> getUsuarioPerfis() {
		return usuarioPerfis;
	}
	public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
}
