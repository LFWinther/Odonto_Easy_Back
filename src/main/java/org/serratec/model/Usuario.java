package org.serratec.model;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "nome_usuario", nullable = false, length = 40)
    private String nome;
    
    @NotBlank
    @CPF(message = "CPF Inválido")
    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;
    
    @NotBlank
    @Email(message = "E-mail inválido")
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    
    @NotBlank
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "nascimento", nullable = false)
    private LocalDate nascimento;
    
    @NotBlank
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @JsonBackReference
	@OneToMany(mappedBy = "id.usuario", fetch = FetchType.EAGER)
	private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
	public Set<UsuarioPerfil> getUsuarioPerfis() {
		return usuarioPerfis;
	}
	public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
}
