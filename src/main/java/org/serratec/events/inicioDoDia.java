package org.serratec.events;

import javax.mail.MessagingException;

import org.serratec.exception.AgendamentoException;
import org.serratec.exception.EmailException;
import org.serratec.repository.AgendamentoRepository;
import org.serratec.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class inicioDoDia {		
	@Autowired
	AgendamentoRepository agendamentoRepository;
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@Scheduled(cron = "0 0 6 * * *")
	public void enviarEmails() throws EmailException, AgendamentoException, MessagingException {
    	agendamentoService.AgendamentosProxDia();
    	agendamentoService.emailAgradecimento();
	}
	
	//@Scheduled(fixedRate = 60000)
	@Scheduled(cron = "0 0 0 * * *")
    public void atualizarDia() throws EmailException, AgendamentoException, MessagingException {
		agendamentoService.setarTodosDiaSemana();
    }
}
