package org.serratec.repository;

import org.serratec.model.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicosRepository extends JpaRepository<Servicos, Long>{
    public Servicos findByNome(String nome);
}
