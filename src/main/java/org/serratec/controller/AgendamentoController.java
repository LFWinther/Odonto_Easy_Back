package org.serratec.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.serratec.dto.AgendamentoDTO;
import org.serratec.enums.EHora;
import org.serratec.exception.AgendamentoException;
import org.serratec.exception.EmailException;
import org.serratec.model.Agendamento;
import org.serratec.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

	@Autowired
	AgendamentoService agendamentoService;
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.ACCEPTED)
    public Agendamento salvar(@RequestBody AgendamentoDTO agendamentoDTO) throws AgendamentoException, EmailException, AgendamentoException, MessagingException {
		return agendamentoService.salvar(agendamentoDTO);
        
    }

    @GetMapping("/buscar/{idAgendamento}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long idAgendamento) throws AgendamentoException {
    	return ResponseEntity.ok(agendamentoService.buscarPorId(idAgendamento));
    }

    @PutMapping("/atualizar/{idAgendamento}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Agendamento atualizar(@PathVariable Long idAgendamento, @RequestBody AgendamentoDTO agendamentoDTO) {
        return agendamentoService.atualizar(idAgendamento, agendamentoDTO);
    }

    @DeleteMapping("/delete/{idAgendamento}")
    public ResponseEntity<String> delete(@PathVariable Long idAgendamento) throws AgendamentoException{
        return new ResponseEntity<>(agendamentoService.delete(idAgendamento),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Agendamento>> listaTodos(){
        return new ResponseEntity<>(agendamentoService.todosAgendamentos(),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/email-pre-consulta")
    public ResponseEntity<String> emailPreConsulta() throws EmailException, AgendamentoException, MessagingException {
    	agendamentoService.AgendamentosProxDia();
        return new ResponseEntity<String>( HttpStatus.CREATED);
    }
    
    @GetMapping("/email-pos-consulta")
    public ResponseEntity<String> emailPosConsulta() throws EmailException, AgendamentoException, MessagingException {
    	agendamentoService.emailAgradecimento();
    	return new ResponseEntity<String>( HttpStatus.CREATED);
    }

    @GetMapping("/filtro/{tipo}/{value}")
    public ResponseEntity<List<Agendamento>> FiltrarCampo(@PathVariable Integer tipo, @PathVariable String value) throws AgendamentoException{
        return new ResponseEntity<>(agendamentoService.FiltrarCampo(tipo, value),
                HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/horarios/{data}")
    public ResponseEntity<List<EHora>> horariosDisponiveis(@PathVariable String data) throws AgendamentoException{
        return new ResponseEntity<>(agendamentoService.Horarios(data),
                HttpStatus.ACCEPTED);
    }
}
