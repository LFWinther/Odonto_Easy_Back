package org.serratec.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.serratec.enums.EHora;
import org.serratec.enums.EStatus;

@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long IdAgendamento;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "dia_agendamento", nullable = false)
	private LocalDate dia;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "hora_agendamento", nullable = false)
	private EHora hora;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_agendamento", nullable = false)
	private EStatus status;
	
    @Column(name = "descricao", length = 250)
    private String descricao;

	@Column(name = "dia_semana_agendamento")
	private String diaSemana;

	@ManyToOne
	@JoinColumn(name="id_funcionario", referencedColumnName = "id_usuario", nullable=false)
	private Usuario funcionario;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", referencedColumnName = "id_usuario", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_servico", referencedColumnName = "id_servico", nullable=false)
	private Servicos servico;
	
	public Agendamento() {
		
	}

	public Long getIdAgendamento() {
		return IdAgendamento;
	}
	public void setIdAgendamento(Long idAgendamento) {
		IdAgendamento = idAgendamento;
	}
	public LocalDate getDia() {
		return dia;
	}
	public void setDia(LocalDate dia) {
		this.dia = dia;
	}
	public EHora getHora() {
		return hora;
	}
	public void setHora(EHora hora) {
		this.hora = hora;
	}
	public EStatus getStatus() {
		return status;
	}
	public void setStatus(EStatus status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Usuario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Servicos getServico() {
		return servico;
	}
	public void setServico(Servicos servico) {
		this.servico = servico;
	}
}
