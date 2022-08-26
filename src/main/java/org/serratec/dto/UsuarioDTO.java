package org.serratec.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.serratec.model.Endereco;
import org.serratec.model.Usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioDTO {

	private Long id;
    private String nome;
	private String cpf;
    private String email;
    private String password;
    private Endereco endereco;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;
    
    private String telefone;
	private List<Map<String, String>> perfis;
    
    public UsuarioDTO(Usuario usuario){
    	this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.endereco = usuario.getEndereco();
        this.nascimento = usuario.getNascimento();
        this.telefone = usuario.getTelefone();
        this.perfis=  usuario.getUsuarioPerfis().stream().map(up -> { 
            Map<String,String> perfil = new HashMap<>();
            perfil.put("id",up.getPerfil().getId().toString());
            perfil.put("perfil", up.getPerfil().getNome());
            return perfil;
            }).collect(Collectors.toList());
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Map<String, String>> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Map<String, String>> perfis) {
		this.perfis = perfis;
	}
}
