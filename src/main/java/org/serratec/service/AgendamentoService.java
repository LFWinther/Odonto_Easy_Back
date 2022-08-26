package org.serratec.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.serratec.dto.AgendamentoDTO;
import org.serratec.enums.EHora;
import org.serratec.enums.EStatus;
import org.serratec.exception.AgendamentoException;
import org.serratec.exception.EmailException;
import org.serratec.model.Agendamento;
import org.serratec.model.Usuario;
import org.serratec.model.Servicos;
import org.serratec.repository.AgendamentoRepository;
import org.serratec.repository.UsuarioRepository;
import org.serratec.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
	
	@Autowired
	AgendamentoRepository agendamentoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ServicosRepository servicosRepository;
	
	@Autowired
	EmailService emailService;
	
	public Agendamento toModel(Agendamento agendamento, AgendamentoDTO agendamentoDTO) {
		agendamento.setDia(agendamentoDTO.getDia());
		agendamento.setHora(agendamentoDTO.getHora());
		agendamento.setStatus(agendamentoDTO.getStatus());
		agendamento.setDescricao(agendamentoDTO.getDescricao());
		agendamento.setDiaSemana(agendamentoDTO.getDiaSemana());

		if(agendamentoDTO.getIdUsuario() != null && agendamentoDTO.getIdFuncionario() != null ) {
			Optional<Usuario> usuario = usuarioRepository.findById(agendamentoDTO.getIdUsuario());
			agendamento.setUsuario(usuario.get());
		}
		
		if(agendamentoDTO.getIdFuncionario() != null ) {
			Optional<Usuario> funcionario = usuarioRepository.findById(agendamentoDTO.getIdFuncionario());
			agendamento.setFuncionario(funcionario.get());
		}
		
		if(agendamentoDTO.getIdServico() != null ) {
			Optional<Servicos> servico = servicosRepository.findById(agendamentoDTO.getIdServico());
			agendamento.setServico(servico.get());
		}
		
        return agendamento;
    }

    public AgendamentoDTO toDTO(AgendamentoDTO agendamentoDTO, Agendamento agendamento) {
    	agendamentoDTO.setIdAgendamento(agendamento.getIdAgendamento());
    	agendamentoDTO.setDia(agendamento.getDia());
    	agendamentoDTO.setHora(agendamento.getHora());
    	agendamentoDTO.setStatus(agendamento.getStatus());
		agendamentoDTO.setDescricao(agendamento.getDescricao());
    	agendamentoDTO.setIdUsuario(agendamento.getUsuario().getId());
    	agendamentoDTO.setIdFuncionario(agendamento.getFuncionario().getId());
    	agendamentoDTO.setIdServico(agendamento.getServico().getId());
        return agendamentoDTO;
    }

    public Agendamento salvar(AgendamentoDTO agendamentoDTO) throws EmailException, AgendamentoException, MessagingException {
    	Agendamento agendamento = new Agendamento();
    	toModel(agendamento, agendamentoDTO);
    	setarDiaSemana(agendamento);
    	agendamentoRepository.save(agendamento);
        return agendamento;
    }

    public Agendamento buscarPorId(Long idAgendamento) {
        return agendamentoRepository.findById(idAgendamento).get();
    }

    public String delete(Long idAgendamento) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(idAgendamento);

        if (agendamentoOptional.isPresent()) {
        	agendamentoRepository.deleteById(idAgendamento);
            return "Agendamento deletado com sucesso!";
        }
        throw new AgendamentoException("Agendamento não encontrado");
    }

    public Agendamento atualizar(Long idAgendamento, AgendamentoDTO agendamentoDTO) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(idAgendamento);
        Agendamento agendamento = new Agendamento();
        if(agendamentoOptional.isPresent()) {
        	agendamento = agendamentoOptional.get();
        }
        
        if(agendamentoDTO.getDia() != null) {
            agendamento.setDia(agendamentoDTO.getDia());
        }
        
        if(agendamentoDTO.getHora() != null) {
            agendamento.setHora(agendamentoDTO.getHora());
        }
        
        if(agendamentoDTO.getDescricao() != null) {
            agendamento.setDescricao(agendamentoDTO.getDescricao());
        }
        
        if(agendamentoDTO.getStatus() != null) {
            agendamento.setStatus(agendamentoDTO.getStatus());
        }
        
        agendamentoRepository.save(agendamento);
        return agendamento;
    }

	    public List<Agendamento> todosAgendamentos(){
			return agendamentoRepository.findAll();
    }
    
    public String AgendamentosProxDia() throws EmailException, AgendamentoException, MessagingException{
    	List<Agendamento> AgendaAmanha = agendamentoRepository.findAll();
    	for (Agendamento agendamento : AgendaAmanha) {
    		if (LocalDate.now().plusDays(1).equals(agendamento.getDia())) {
    			emailService.emailConfirmarConsulta(agendamento);
    		}
    	}
    	return "Emails envidos com sucesso.";
    }
    
    public String emailAgradecimento() throws EmailException, AgendamentoException, MessagingException {
    	List<Agendamento> AgendaAmanha = agendamentoRepository.findAll();
    	for (Agendamento agendamento : AgendaAmanha) {
    		if (LocalDate.now().equals(agendamento.getDia().plusDays(1)) && agendamento.getStatus().equals(EStatus.FINALIZADO)) {
    			emailService.emailPosConsulta(agendamento);
    		}
    	}
    	return "Emails envidos com sucesso.";
    	
    }
    
    public void setarTodosDiaSemana() throws EmailException, AgendamentoException, MessagingException {
		List<Agendamento> Agenda = agendamentoRepository.findAll();
		for (Agendamento agendamento : Agenda) {
			setarDiaSemana(agendamento);
		}
	}
    
    public void setarDiaSemana(Agendamento agendamento) throws EmailException, AgendamentoException, MessagingException {
    	if(LocalDate.now().isAfter(agendamento.getDia())) {
			agendamento.setDiaSemana("Já passou");
			
		} else if(LocalDate.now().equals(agendamento.getDia())) {
			agendamento.setDiaSemana("Hoje");
			
		} else if(LocalDate.now().plusDays(1).equals(agendamento.getDia())) {
			agendamento.setDiaSemana("Amanhã");
			
		} else if(LocalDate.now().plusWeeks(1).isAfter(agendamento.getDia())) {
			agendamento.setDiaSemana("Esta semana");
			
		} else if(LocalDate.now().getMonth().equals(agendamento.getDia().getMonth())) {
			agendamento.setDiaSemana("Este mês");
			
		} else {
			agendamento.setDiaSemana("Nos próximos meses");
		}
		
		agendamentoRepository.save(agendamento);
	}

    public List<Agendamento> FiltrarCampo(Integer tipo, String value){
    	if (tipo == 1) {
    		return agendamentoRepository.filterByNomeUsuario(value);
    	} else if (tipo == 2) {
    		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    		LocalDate data = LocalDate.parse(value, fmt);
    		return agendamentoRepository.filterByData(data);
    	} else if (tipo == 3) {
    		return agendamentoRepository.filterByStatus(value);
    	} else if (tipo == 4) {
    		return agendamentoRepository.filterByServico(value);
    	} else if (tipo == 5) {
    		return agendamentoRepository.filterByServicoPreco(value);
    	} else if (tipo == 6) {
    		return agendamentoRepository.filterByIdUsuario(Long.parseLong(value));
    	}
    	
    	return null;
	}

    public List<EHora> Horarios(String data){
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		LocalDate dia = LocalDate.parse(data, fmt);
		List<Agendamento> agendamentos = agendamentoRepository.filterByData(dia);
		
        List<EHora> hora = Arrays.asList(EHora.values());
        List<EHora> horarios = new ArrayList<EHora>();
        
        for (int i = 0; i < hora.size(); i++) {
        	horarios.add(hora.get(i));
        }
        
    	for (int i = 0; i < hora.size(); i++) {
    		for (int a = 0; a < agendamentos.size(); a++) {
        		if (agendamentos.get(a).getHora().getLocalTime() == hora.get(i).getLocalTime() && agendamentos.get(a).getStatus().getCodigo() == 1) {
        			horarios.remove(hora.get(i));
        		}
        	}
        }
        return horarios;
	}
    
    
}
