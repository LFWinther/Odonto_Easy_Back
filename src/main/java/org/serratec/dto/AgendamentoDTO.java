package org.serratec.dto;

import java.time.LocalDate;

import org.serratec.enums.EHora;
import org.serratec.enums.EStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgendamentoDTO {

	private Long IdAgendamento;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dia;
	
	private EHora hora;
	private EStatus status;
	private String descricao;
	private String diaSemana;
	private Long idFuncionario;
	private Long idUsuario;
	private Long idServico;

	public AgendamentoDTO() {
		
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
	
	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

}
