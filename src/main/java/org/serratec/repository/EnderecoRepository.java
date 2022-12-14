package org.serratec.repository;

import org.serratec.model.Cep;
import org.serratec.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    public Cep findByCep(String cep);
}
