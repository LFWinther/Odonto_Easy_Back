package org.serratec.model;


import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Servicos {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Long id;
    
    @NotBlank
    @Column(name = "nome", nullable = false, length = 40)
    @Size(max = 40, min = 3)
    private String nome;
    
    @NotBlank
    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;
    
    @DecimalMin(value = "0.0")
    @Digits(integer=18, fraction=2)
    @Column(name = "preco")
    private BigDecimal preco;
    
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "tempo_servico", nullable = false, length = 200)
    private LocalTime tempoServico;

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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public LocalTime getTempoServico() {
		return tempoServico;
	}
	public void setTempoServico(LocalTime tempoServico) {
		this.tempoServico = tempoServico;
	}
}
