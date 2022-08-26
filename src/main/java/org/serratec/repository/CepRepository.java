package org.serratec.repository;

import org.serratec.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<Cep, Long>{
    public Cep findByCep(String cep);
}
