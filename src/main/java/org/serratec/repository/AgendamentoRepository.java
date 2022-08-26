package org.serratec.repository;

import java.time.LocalDate;
import java.util.List;

import org.serratec.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	@Query(value="FROM Agendamento a WHERE a.usuario.nome LIKE %?1% ORDER BY a.dia, a.hora")
    List<Agendamento> filterByNomeUsuario(String nomeUsuario);
	
	@Query(value="FROM Agendamento a WHERE a.dia = ?1 ORDER BY a.dia, a.hora")
    List<Agendamento> filterByData(LocalDate data);
	
	//INCOMPLETO
	@Query(value="FROM Agendamento a WHERE a.status LIKE %?1% ORDER BY a.dia, a.hora")
    List<Agendamento> filterByStatus(String status);
	
	@Query(value="FROM Agendamento a WHERE a.servico.nome LIKE %?1% ORDER BY a.dia, a.hora")
    List<Agendamento> filterByServico(String servico);
	
	//INCOMPLETO
	@Query(value="FROM Agendamento a WHERE a.servico.preco = ?1 ORDER BY a.dia, a.hora")
    List<Agendamento> filterByServicoPreco(String servicoPreco);

	@Query(value="FROM Agendamento a WHERE a.usuario.id = ?1 ORDER BY a.dia, a.hora")
    List<Agendamento> filterByIdUsuario(Long idUsuario);
}
